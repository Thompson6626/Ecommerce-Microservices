package com.thmz.order.order;

import com.thmz.order.order.dto.OrderRequest;
import com.thmz.order.order.dto.OrderResponse;
import com.thmz.order.orderline.OrderLine;
import com.thmz.order.orderline.dto.OrderLineResponse;
import org.springframework.stereotype.Service;

@Service
public class OrderMapper {


    public Order toOrder(OrderRequest request) {
        return Order.builder()
                .id(request.id())
                .customerId(request.customerId())
                .reference(request.reference())
                .totalAmount(request.amount())
                .paymentMethod(request.paymentMethod())
                .build();
    }

    public OrderResponse toResponse(Order order) {
        return OrderResponse.builder()
                .id(order.getId())
                .customerId(order.getCustomerId())
                .reference(order.getReference())
                .amount(order.getTotalAmount())
                .paymentMethod(order.getPaymentMethod())
                .build();
    }

    public OrderLineResponse toOrderLineResponse(OrderLine orderLine) {
        return
    }
}
