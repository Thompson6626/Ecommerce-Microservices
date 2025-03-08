package com.thmz.ecommerce.kafka.order;

import com.thmz.ecommerce.kafka.payment.PaymentMethod;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;

@Builder
@Getter
public class OrderConfirmation {

    private String orderReference;
    private BigDecimal totalAmount;

    private PaymentMethod paymentMethod;
    private Customer customer;
    private List<Product> products;
}
