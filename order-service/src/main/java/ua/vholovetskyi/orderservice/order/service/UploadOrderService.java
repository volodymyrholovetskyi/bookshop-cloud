package ua.vholovetskyi.orderservice.order.service;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ua.vholovetskyi.orderservice.commons.exception.impl.order.UploadOrderException;
import ua.vholovetskyi.orderservice.customer.model.CustomerEntity;
import ua.vholovetskyi.orderservice.customer.repository.CustomerRepository;
import ua.vholovetskyi.orderservice.order.controller.dto.UploadOrderResponse;
import ua.vholovetskyi.orderservice.order.model.OrderEntity;
import ua.vholovetskyi.orderservice.order.validator.OrderJson;
import ua.vholovetskyi.orderservice.order.validator.OrderJsonValidator;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static ua.vholovetskyi.orderservice.order.mapper.OrderFactory.createNewOrder;

/**
 * @author Volodymyr Holovetskyi
 * @version 1.0
 * @since 2024-04-24
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class UploadOrderService {

    private final ObjectMapper objectMapper;
    private final CustomerRepository customerRepository;
    private final OrderJsonValidator validator;
    private final BatchOrderService batchOrderService;

    public UploadOrderResponse uploadOrders(String fileName, InputStream inputStream) {
        return parseOrders(fileName, inputStream);
    }

    private UploadOrderResponse parseOrders(String fileName, InputStream inputStream) {
        log.info("Start parse uploaded %s file...".formatted(fileName));
        var orders = new ArrayList<OrderEntity>();
        var response = new UploadOrderResponse();
        try (JsonParser jsonParser = objectMapper.createParser(inputStream)) {
            if (jsonParser.nextToken() == JsonToken.START_ARRAY) {
                while (jsonParser.nextToken() != JsonToken.END_ARRAY) {
                    var orderJson = objectMapper.readValue(jsonParser, OrderJson.class);
                    response.countRecord(addOrder(orderJson, orders));
                    batchOrderService.batchProcessing(orders, false);
                }
            }
            batchOrderService.batchProcessing(orders, true);
            log.info("End parse uploaded %s file...".formatted(fileName));
        } catch (IOException e) {
            log.warn("Error in parseOrders() method! Error message: %s".formatted(e.getMessage()));
            throw new UploadOrderException(e);
        }
        return response;
    }

    private boolean addOrder(OrderJson order, List<OrderEntity> orders) {
        if (isValid(order)) {
            return orders.add(createNewOrder(order));
        }
        return false;
    }

    private boolean isValid(OrderJson orderJson) {
        return validator.validateOrder(orderJson) && findById(orderJson.getCustId()).isPresent();
    }

    private Optional<CustomerEntity> findById(Long id) {
        return customerRepository.findById(id);
    }
}
