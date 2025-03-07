package com.thmz.ecommerce.common;

public record SimpleResponse<T>(T data) {

    public static <V> SimpleResponse<V> of(V value) {
        return new SimpleResponse<>(value);
    }

}

