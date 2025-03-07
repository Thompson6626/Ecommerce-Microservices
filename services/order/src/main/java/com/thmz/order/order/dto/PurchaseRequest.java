package com.thmz.order.order.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record PurchaseRequest(
        @NotNull(message = "Product is required")
        Integer productId,
        @Positive(message = "Quantity is required")
        Double quantity
) {
}
