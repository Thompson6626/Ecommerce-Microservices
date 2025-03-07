package com.thmz.order.product;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Builder
@Getter
public class PurchaseResponse {

    private Integer productId;
    private String name;

    private String description;
    private BigDecimal price;
    private Double quantity;
}
