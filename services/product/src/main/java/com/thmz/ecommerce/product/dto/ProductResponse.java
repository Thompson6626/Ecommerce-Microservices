package com.thmz.ecommerce.product.dto;


import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Builder
@Getter
public class ProductResponse {

    private Integer id;
    private String name;
    private String description;
    private Double availableQuantity;
    private BigDecimal price;
    private Integer categoryId;
    private String categoryName;
    private String categoryDescription;

}
