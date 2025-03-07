package com.thmz.ecommerce.customer;

import com.thmz.ecommerce.common.SimpleResponse;
import com.thmz.ecommerce.customer.dto.CustomerRequest;
import com.thmz.ecommerce.customer.dto.CustomerResponse;
import com.thmz.ecommerce.exceptions.CustomerNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.apache.commons.lang.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerService {


    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public SimpleResponse<String> createCustomer(@Valid CustomerRequest request) {
        var customer = customerRepository.save(customerMapper.toCustomer(request));

        return SimpleResponse.of(
                customer.getId()
        );
    }

    public void updateCustomer(@Valid CustomerRequest request) {
        var customer = customerRepository.findById(request.id())
                .orElseThrow(() -> new CustomerNotFoundException(
                        String.format("Could not update customer with id %s",request.id())
                ));
        mergeCustomer(customer,request);

        customerRepository.save(customer);
    }

    private void mergeCustomer(Customer customer, CustomerRequest request) {
        if (StringUtils.isNotBlank(request.firstName())){
            customer.setFirstName(request.firstName());
        }
        if (StringUtils.isNotBlank(request.lastName())){
            customer.setLastName(request.lastName());
        }
        if (StringUtils.isNotBlank(request.email())){
            customer.setEmail(request.email());
        }
        if (request.address() != null){
            customer.setAddress(request.address());
        }
    }

    public List<CustomerResponse> findAllCustomers() {
        return customerRepository.findAll().stream()
                .map(customerMapper::toResponse)
                .collect(Collectors.toList());
    }

    public SimpleResponse<Boolean> existsById(String customerId) {
        return SimpleResponse.of(
                customerRepository.existsById(customerId)
        );
    }

    public CustomerResponse findById(String customerId) {
        return customerRepository.findById(customerId)
                .map(customerMapper::toResponse)
                .orElseThrow(() -> new CustomerNotFoundException(
                        String.format("Could not find customer with id %s",customerId)
                ));
    }

    public void deleteCustomer(String customerId) {
        customerRepository.deleteById(customerId);
    }
}
