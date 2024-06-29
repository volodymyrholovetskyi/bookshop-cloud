package ua.vholovetskyi.orderservice.order.service.handler;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ua.vholovetskyi.orderservice.order.controller.dto.SearchRequest;
import ua.vholovetskyi.orderservice.order.model.OrderEntity;
import ua.vholovetskyi.orderservice.order.repository.OrderRepository;

import java.util.Objects;

public class CustIDSearchHandler implements OrderSearchHandler {
    private final OrderRepository orderRepository;

    public CustIDSearchHandler(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public boolean supports(SearchRequest filter) {
        return Objects.isNull(filter.getStatus())
                && Objects.isNull(filter.getFrom())
                && Objects.isNull(filter.getTo());
    }

    @Override
    public Page<OrderEntity> handle(SearchRequest filter, Pageable pageable) {
        return orderRepository.findAllByCustomerId(filter.getCustomerId(), pageable);
    }
}
