package ua.vholovetskyi.orderservice.commons.exception.impl.order;

import ua.vholovetskyi.orderservice.commons.exception.BusinessException;

/**
 * @author Volodymyr Holovetskyi
 * @version 1.0
 * @since 2024-04-22
 */
public class ReportOrderException extends BusinessException {
    public ReportOrderException(String message, Throwable cause) {
        super(message, cause);
    }
}
