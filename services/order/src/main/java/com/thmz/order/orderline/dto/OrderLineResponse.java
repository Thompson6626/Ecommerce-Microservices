package com.thmz.order.orderline.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class OrderLineResponse{
    private Integer id;
    private Double quantity;
}
