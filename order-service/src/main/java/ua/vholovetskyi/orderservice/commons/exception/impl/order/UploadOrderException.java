package ua.vholovetskyi.orderservice.commons.exception.impl.order;

import ua.vholovetskyi.orderservice.commons.exception.BusinessException;

/**
 * @author Volodymyr Holovetskyi
 * @version 1.0
 * @since 2024-04-22
 */
public class UploadOrderException extends BusinessException {
    public UploadOrderException(Throwable cause) {
        super("An error occurred while uploading the file!", cause);
    }
}
