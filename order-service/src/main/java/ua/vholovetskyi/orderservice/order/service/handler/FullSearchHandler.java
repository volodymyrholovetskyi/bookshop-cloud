package ua.vholovetskyi.orderservice.order.service.handler;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ua.vholovetskyi.orderservice.order.controller.dto.SearchRequest;
import ua.vholovetskyi.orderservice.order.model.OrderEntity;
import ua.vholovetskyi.orderservice.order.repository.OrderRepository;

import java.util.Objects;

public class FullSearchHandler implements OrderSearchHandler {

    private final OrderRepository orderRepository;

    public FullSearchHandler(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public boolean supports(SearchRequest filter) {
        return Objects.nonNull(filter.getStatus())
                && Objects.nonNull(filter.getFrom())
                && Objects.nonNull(filter.getTo());
    }

    @Override
    public Page<OrderEntity> handle(SearchRequest filter, Pageable pageable) {
        return orderRepository.findAllByCustomerIdAndOrderDateIsBetweenAndStatus(
                filter.getCustomerId(),
                filter.getFrom(),
                filter.getTo(),
                filter.getStatus(),
                pageable);
    }
}
