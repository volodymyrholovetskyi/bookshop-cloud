package ua.vholovetskyi.orderservice.order.mapper;

import ua.vholovetskyi.orderservice.commons.publisher.NotificationMessageDto;
import ua.vholovetskyi.orderservice.customer.model.CustomerEntity;
import ua.vholovetskyi.orderservice.order.model.OrderEntity;

import java.util.Map;

public class OrderNotificationFactory {

    private static final String SUBJECT = "Create Order";
    private static final String TEMPLATE_NAME = "order-template";

    public static NotificationMessageDto createNotification(CustomerEntity customer, OrderEntity order) {
        return NotificationMessageDto.builder()
                .to(customer.getEmail())
                .subject(SUBJECT)
                .body(Map.of(
                        "orderId", order.getId(),
                        "status", order.getStatus(),
                        "fullName", customer.getFullName()))
                .templateName(TEMPLATE_NAME)
                .build();
    }
}
