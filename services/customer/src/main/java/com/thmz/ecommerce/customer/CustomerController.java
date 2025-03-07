package com.thmz.ecommerce.customer;

import com.thmz.ecommerce.common.SimpleResponse;
import com.thmz.ecommerce.customer.dto.CustomerRequest;
import com.thmz.ecommerce.customer.dto.CustomerResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<SimpleResponse<String>> createCustomer(
            @RequestBody @Valid CustomerRequest request
    ){
        return ResponseEntity.ok(customerService.createCustomer(request));
    }

    @PutMapping
    public ResponseEntity<Void> updateCustomer(
            @RequestBody @Valid CustomerRequest request
    ){
        customerService.updateCustomer(request);
        return ResponseEntity.accepted().build();
    }

    @GetMapping
    public ResponseEntity<List<CustomerResponse>> findAll() {
        return ResponseEntity.ok(customerService.findAllCustomers());
    }

    @GetMapping("/exists/{customer-id}")
    public ResponseEntity<SimpleResponse<Boolean>> existsById(
            @PathVariable("customer-id")String customerId
    ){
        return ResponseEntity.ok(customerService.existsById(customerId));
    }

    @GetMapping("/{customer-id}")
    public ResponseEntity<CustomerResponse> findById(
            @PathVariable("customer-id")String customerId
    ){
        return ResponseEntity.ok(customerService.findById(customerId));
    }

    @DeleteMapping("/{customer-id}")
    public ResponseEntity<Void> deleteById(
            @PathVariable("customer-id") String customerId
    ){
        customerService.deleteCustomer(customerId);
        return ResponseEntity.noContent().build();
    }
}
