package com.thmz.order.orderline.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class OrderLineRequest {

    private Integer id;

    private Integer orderId;
    private Integer productId;
    private Double quantity;

}
