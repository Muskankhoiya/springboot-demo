package com.springboot.demo.sealedclas;

import com.springboot.demo.entity.Employee;

public final class SuccessEmployeeResponse extends EmployeeResponse{
    private final Employee employee;

    public SuccessEmployeeResponse(String message, Employee employee) {
        super(message);
        this.employee = employee;
    }
    public Employee getEmployee() {
        return employee;
    }
}
