package com.thmz.ecommerce.kafka.order;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Builder
@Getter
public class Product {

    private Integer productId;
    private String name;

    private String description;
    private BigDecimal price;
    private Double quantity;
}
