package com.thmz.order.order.dto;

import com.thmz.order.order.PaymentMethod;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.List;

public record OrderRequest(
        Integer id,
        String reference,
        @Positive(message = "Order amount should be positive")
        BigDecimal amount,
        @NotNull(message = "Payment method should be precised")
        PaymentMethod paymentMethod,
        @NotBlank(message = "Customer should be specified")
        String customerId,
        @NotEmpty(message = "At least 1 product is required")
        List<PurchaseRequest> products
) {
}
