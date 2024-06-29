package ua.vholovetskyi.orderservice.order.validator;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ua.vholovetskyi.orderservice.commons.annotation.DateFormatValidation;
import ua.vholovetskyi.orderservice.commons.annotation.StatusValidation;

import java.math.BigDecimal;

/**
 * @author Volodymyr Holovetskyi
 * @version 1.0
 * @since 2024-04-24
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrderJson {
    @NotNull
    private Long custId;
    @NotNull
    @DecimalMin(value = "0.00")
    private BigDecimal grossValue;
    @StatusValidation
    private String status;
    @DateFormatValidation
    private String orderDate;
}
