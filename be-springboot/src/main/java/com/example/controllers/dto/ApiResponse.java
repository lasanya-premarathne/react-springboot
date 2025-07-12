package com.example.controllers.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ApiResponse<T> {
    private T data;
    private String message;
    private LocalDateTime timestamp;
    private boolean success;

    public static <T> ApiResponse<T> success(T data, String message) {
        ApiResponse<T> response = new ApiResponse<>();
        response.setData(data);
        response.setMessage(message);
        response.setTimestamp(LocalDateTime.now());
        response.setSuccess(true);
        return response;
    }

    public static <T> ApiResponse<T> error(String message) {
        ApiResponse<T> response = new ApiResponse<>();
        response.setMessage(message);
        response.setTimestamp(LocalDateTime.now());
        response.setSuccess(false);
        return response;
    }
}