package com.thmz.order.kafka;

import com.thmz.order.customer.CustomerResponse;
import com.thmz.order.order.PaymentMethod;
import com.thmz.order.product.PurchaseResponse;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;

@Builder
@Getter
public class OrderConfirmation{
        private String orderReference;
        private BigDecimal totalAmount;
        private PaymentMethod paymentMethod;
        private CustomerResponse customer;
        private List<PurchaseResponse> products;
}
