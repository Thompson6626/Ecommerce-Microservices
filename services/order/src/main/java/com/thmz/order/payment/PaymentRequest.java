package com.thmz.order.payment;

import com.thmz.order.customer.CustomerResponse;
import com.thmz.order.order.PaymentMethod;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Builder
@Getter
public class PaymentRequest{
        private BigDecimal amount;
        private PaymentMethod paymentMethod;
        private Integer orderId;
        private String orderReference;
        private CustomerResponse customer;

}