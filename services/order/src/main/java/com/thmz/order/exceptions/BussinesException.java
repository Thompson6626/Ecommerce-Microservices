package com.thmz.order.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class BussinesException extends RuntimeException {
    private final String message;
}
