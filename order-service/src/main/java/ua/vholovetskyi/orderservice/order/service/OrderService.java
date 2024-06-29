package ua.vholovetskyi.orderservice.order.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.vholovetskyi.orderservice.commons.exception.impl.customer.CustomerNotFoundException;
import ua.vholovetskyi.orderservice.commons.exception.impl.order.OrderNotFoundException;
import ua.vholovetskyi.orderservice.commons.publisher.NotificationPublisher;
import ua.vholovetskyi.orderservice.customer.model.CustomerEntity;
import ua.vholovetskyi.orderservice.customer.repository.CustomerRepository;
import ua.vholovetskyi.orderservice.order.controller.dto.OrderDto;
import ua.vholovetskyi.orderservice.order.controller.dto.OrderSummary;
import ua.vholovetskyi.orderservice.order.model.OrderEntity;
import ua.vholovetskyi.orderservice.order.repository.OrderRepository;

import static ua.vholovetskyi.orderservice.order.mapper.OrderFactory.createNewOrder;
import static ua.vholovetskyi.orderservice.order.mapper.OrderFactory.createOrderSummary;

/**
 * @author Volodymyr Holovetskyi
 * @version 1.0
 * @since 2024-04-24
 */
@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final NotificationPublisher notificationPublisher;

    public OrderSummary createOrder(OrderDto orderDto) {
        var loadCustomer = validateCustomerExists(orderDto.getCustomerId());
        var savedOrder = orderRepository.save(createNewOrder(orderDto));
//        notificationPublisher.publishNotification(createNotification(loadCustomer, savedOrder));
        return createOrderSummary(savedOrder);
    }

    public OrderEntity updateOrder(Long id, OrderDto orderDto) {
        return orderRepository.findById(id)
                .map(loadOrder -> {
                    validateCustomerExists(orderDto.getCustomerId());
                    loadOrder.updateFields(orderDto);
                    return orderRepository.save(loadOrder);
                }).orElseThrow(() -> new OrderNotFoundException(id));
    }

    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }

    private CustomerEntity validateCustomerExists(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException(id));
    }
}
