package com.thmz.ecommerce.payment;

import com.thmz.ecommerce.notification.NotificationProducer;
import com.thmz.ecommerce.notification.PaymentNotificationRequest;
import com.thmz.ecommerce.payment.dto.PaymentRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;

    private final NotificationProducer notificationProducer;

    public Integer createRequest(PaymentRequest request) {
        var payment = paymentRepository.save(
                paymentMapper.toPayment(request)
        );

        notificationProducer.sendNotification(
                PaymentNotificationRequest.builder()
                        .customerFirstname(request.customer().firstname())
                        .customerLastname(request.customer().lastname())
                        .customerEmail(request.customer().email())
                        .amount(payment.getAmount())
                        .paymentMethod(payment.getPaymentMethod())
                        .orderReference(request.orderReference())
                        .build()
        );

        return payment.getId();


    }
}
