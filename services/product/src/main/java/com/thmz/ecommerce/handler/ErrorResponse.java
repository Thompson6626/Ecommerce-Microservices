package com.thmz.ecommerce.handler;

import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
@Builder
public class ErrorResponse {
    private Map<String,String> errors;
}
