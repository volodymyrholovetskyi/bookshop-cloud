package ua.vholovetskyi.orderservice.order.controller.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import ua.vholovetskyi.orderservice.order.model.OrderStatus;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @author Volodymyr Holovetskyi
 * @version 1.0
 * @since 2024-04-22
 */
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {
    @NotNull(message = "{order.filed.required}")
    private Long customerId;
    @NotNull(message = "{order.filed.required}")
    private OrderStatus status;
    @NotNull
    @DecimalMin(value = "0.00")
    private BigDecimal grossValue;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "{order.filed.required}")
    private LocalDate orderDate;

}
