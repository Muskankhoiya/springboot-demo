package com.springboot.demo.service;

import com.springboot.demo.entity.Employee;
import com.springboot.demo.repository.EmployeeRepository;
import com.springboot.demo.sealedclas.EmployeeResponse;
import com.springboot.demo.sealedclas.ErrorEmployeeResponse;
import com.springboot.demo.sealedclas.SuccessEmployeeResponse;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

//    // Java 17: Pattern Matching for instanceof
//    public String getEmployeeName(Object employeeObj) {
//        if (employeeObj instanceof Employee employee) {
//            return employee.getName();
//        }
//        return "Unknown";
//    }

    //pstternmatching

//    public String getEmployeeName(Object employeeObj) {
//        if (employeeObj instanceof Employee) {
//            Employee employee = (Employee) employeeObj; // Explicit cast
//            return employee.getName();
//        }
//        return "not found";
//    }

    public String getEmployeeName(Object employeeobj){
        if(employeeobj instanceof Employee employee ){
            return employee.getName();
        }
        return "not found";
    }


    //sealed classes
    public EmployeeResponse getEmployeeById(Long id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isPresent()) {
            Employee emp = employee.get();
            return new SuccessEmployeeResponse("Employee found", emp);
        } else {
            return new ErrorEmployeeResponse("Employee not found", "ERR404");
        }
    }


    public String getEmployeeDetails(Long id) {
        Optional<Employee> employeeOpt = employeeRepository.findById(id);
        return employeeOpt.map(Employee::getName).orElse("Employee not found");
    }
}
