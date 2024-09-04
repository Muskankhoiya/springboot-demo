package com.springboot.demo.sealedclas;

public sealed class EmployeeResponse permits ErrorEmployeeResponse, SuccessEmployeeResponse {

    private final String message;

    public EmployeeResponse(String message) {
        this.message = message;
    }
    public String getMessage() {
        return message;
    }
}

