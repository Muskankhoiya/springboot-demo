package com.springboot.demo.sealedclas;

public final class ErrorEmployeeResponse extends EmployeeResponse {

    public ErrorEmployeeResponse(String message, String err404) {
        super(message);
    }

    public String getErrorCode() {
        return "errorCode";
    }
}
