package ua.vholovetskyi.orderservice.commons.exception.impl.order;

import ua.vholovetskyi.orderservice.commons.exception.ResourceNotFound;

/**
 * @author Volodymyr Holovetskyi
 * @version 1.0
 * @since 2024-04-22
 */
public class OrderNotFoundException extends ResourceNotFound {
    public OrderNotFoundException(Long id) {
        super("Order with ID: %d not found!".formatted(id));
    }
}
