package ua.vholovetskyi.orderservice.commons.exception.impl.customer;

import ua.vholovetskyi.orderservice.commons.exception.ResourceNotFound;

public class CustomerNotFoundException extends ResourceNotFound {
    public CustomerNotFoundException(Long id) {
        super("Customer with ID: %d not found!".formatted(id));
    }
}
