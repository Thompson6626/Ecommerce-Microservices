package com.thmz.ecommerce.kafka.order;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Customer {

    private String id;
    private String firstname;

    private String lastname;
    private String email;





}
