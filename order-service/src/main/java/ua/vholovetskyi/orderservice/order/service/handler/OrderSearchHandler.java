package ua.vholovetskyi.orderservice.order.service.handler;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ua.vholovetskyi.orderservice.order.controller.dto.SearchRequest;
import ua.vholovetskyi.orderservice.order.model.OrderEntity;

public interface OrderSearchHandler {

    public boolean supports(SearchRequest filteringDto);

    public Page<OrderEntity> handle(SearchRequest filter, Pageable pageable);
}
