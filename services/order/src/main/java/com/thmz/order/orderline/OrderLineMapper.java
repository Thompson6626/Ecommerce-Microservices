package com.thmz.order.orderline;

import com.thmz.order.order.Order;
import org.springframework.stereotype.Service;

@Service
public class OrderLineMapper {


    public OrderLine toOrderLine(OrderLineRequest orderLineRequest) {
        return OrderLine.builder()
                .id(orderLineRequest.getId())
                .quantity(orderLineRequest.getQuantity())
                .order(
                        Order.builder()
                        .id(orderLineRequest.getId())
                        .build()
                )
                .productId(orderLineRequest.getProductId())
                .build();
    }
}



