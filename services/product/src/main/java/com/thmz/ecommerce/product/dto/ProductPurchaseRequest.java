package com.thmz.ecommerce.product.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ProductPurchaseRequest {
    @NotNull(message = "Product is mandatory")
    Integer productId;
    @Positive(message = "Quantity is mandatory")
    Double quantity;
}
