package com.thmz.ecommerce.product.dto;


import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Builder
@Getter
public class ProductPurchaseResponse {

    private Integer productId;
    private String name;
    private String description;
    private BigDecimal price;
    private Double quantity;
}
