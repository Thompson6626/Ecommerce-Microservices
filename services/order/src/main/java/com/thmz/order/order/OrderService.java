package com.thmz.order.order;

import com.thmz.order.customer.CustomerClient;
import com.thmz.order.exceptions.BussinesException;
import com.thmz.order.order.dto.OrderRequest;
import com.thmz.order.orderline.OrderLineRequest;
import com.thmz.order.orderline.OrderLineService;
import com.thmz.order.product.ProductClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    private final CustomerClient customerClient;
    private final ProductClient productClient;

    private final OrderLineService orderLineService;

    public Integer createOrder(OrderRequest request) {
        var customer = customerClient.findCustomerById(request.customerId())
                .orElseThrow(() -> new BussinesException(
                        String.format("Customer with id %s not found", request.customerId())
                ));
        productClient.purchaseProducts(request.products());

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

        return null;
    }
}