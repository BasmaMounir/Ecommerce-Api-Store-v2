package com.ecommerce.ecommerce_api.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.management.ConstructorParameters;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse<T> {
    private int results;
    private boolean success;
    private String message;
    private T data;

    public ApiResponse(boolean success, String message) {
        this.message = message;
        this.success = success;
    }

    public ApiResponse(boolean success, String message,T data) {
        this.message = message;
        this.success = success;
        this.data = data;
    }

    public ApiResponse(T data) {
        this.data = data;
    }
}
