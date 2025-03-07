package com.thmz.ecommerce.customer.dto;


import com.thmz.ecommerce.customer.Address;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CustomerResponse {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private Address address;

}
