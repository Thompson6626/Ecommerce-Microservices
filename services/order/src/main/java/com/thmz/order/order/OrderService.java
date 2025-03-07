package com.thmz.order.order;

import com.thmz.order.customer.CustomerClient;
import com.thmz.order.exceptions.BussinesException;
import com.thmz.order.kafka.OrderConfirmation;
import com.thmz.order.kafka.OrderProducer;
import com.thmz.order.order.dto.OrderRequest;
import com.thmz.order.order.dto.OrderResponse;
import com.thmz.order.orderline.dto.OrderLineRequest;
import com.thmz.order.orderline.OrderLineService;
import com.thmz.order.payment.PaymentClient;
import com.thmz.order.payment.PaymentRequest;
import com.thmz.order.product.ProductClient;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    private final CustomerClient customerClient;
    private final ProductClient productClient;

    private final OrderLineService orderLineService;
    private final OrderProducer orderProducer;

    private final PaymentClient paymentClient;

    public Integer createOrder(OrderRequest request) {
        var customer = customerClient.findCustomerById(request.customerId())
                .orElseThrow(() -> new BussinesException(
                        String.format("Customer with id %s not found", request.customerId())
                ));

        var purchasedProducts = productClient.purchaseProducts(request.products());

        var order = orderRepository.save(orderMapper.toOrder(request));

        for(var purchaseRequest : request.products()) {
            orderLineService.saveOrderLine(
                    OrderLineRequest.builder()
                            .id(null)
                            .orderId(order.getId())
                            .productId(purchaseRequest.productId())
                            .quantity(purchaseRequest.quantity())
                            .build()
            );
        }

        paymentClient.requestOrderPayment(
                PaymentRequest.builder()
                .amount(request.amount())
                .paymentMethod(request.paymentMethod())
                .orderId(order.getId())
                .orderReference(order.getReference())
                .customer(customer)
                .build()
        );

        orderProducer.sendOrderConfirmation(
                OrderConfirmation.builder()
                        .customer(customer)
                        .orderReference(request.reference())
                        .totalAmount(request.amount())
                        .paymentMethod(request.paymentMethod())
                        .products(purchasedProducts)
                        .build()
        );

        return order.getId();
    }

    public List<OrderResponse> findAll() {
        return orderRepository.findAll()
                .stream()
                .map(orderMapper::toResponse)
                .collect(Collectors.toList());
    }

    public OrderResponse findById(Integer orderId) {

        return orderRepository.findById(orderId)
                .map(orderMapper::toResponse)
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format("Order with id %s not found", orderId)
                ));
    }

}