package ua.vholovetskyi.orderservice.order.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ua.vholovetskyi.orderservice.order.controller.dto.OrderList;
import ua.vholovetskyi.orderservice.order.controller.dto.SearchRequest;
import ua.vholovetskyi.orderservice.order.service.handler.OrderSearchService;

import java.util.List;

import static ua.vholovetskyi.orderservice.order.mapper.OrderDtoMapper.mapToOrderListDto;

@Service
@RequiredArgsConstructor
public class ExportOrderService {
    private static final Pageable EMPTY_PAGEABLE = null;
    private final OrderSearchService orderSearchService;

    public List<OrderList> exportOrders(SearchRequest searchRequest) {
        var pageOrder = orderSearchService.searchOrders(searchRequest, EMPTY_PAGEABLE);
        return mapToOrderListDto(pageOrder.getContent());
    }
}
