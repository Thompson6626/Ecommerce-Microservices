package com.thmz.order.client;

public record CustomerResponse(
        String id,
        String firstName,
        String lastname,
        String email
) {
}
