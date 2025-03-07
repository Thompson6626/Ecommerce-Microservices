package com.thmz.ecommerce.handler;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.Map;


@JsonInclude(JsonInclude.Include.NON_NULL)
public record ErrorResponse(
        Map<String,String> errors,
        String message
) {

    public static ErrorResponse ofMessage(String message) {
        return new ErrorResponse(
                null,
                message
        );
    }

    public static ErrorResponse ofErrors(Map<String,String> errors){
        return new ErrorResponse(
                errors,
                null
        );
    }



}