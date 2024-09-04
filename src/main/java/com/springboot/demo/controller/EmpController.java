package com.springboot.demo.controller;

import com.springboot.demo.entity.Emp;
import com.springboot.demo.entity.Employee;
import com.springboot.demo.entity.Student;
import com.springboot.demo.sealedclas.EmployeeResponse;
import com.springboot.demo.service.EmployeeService;
import io.micrometer.observation.Observation;
import io.micrometer.observation.ObservationRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/demo")
public class EmpController {

    @Autowired
    private ObservationRegistry observationRegistry;

    @Autowired
    EmployeeService employeeService;

   @GetMapping("/emp")
  public ResponseEntity<Emp> getDetails(){
      var emp = new Emp(2, "muskan");
      return Observation.createNotStarted("emp", observationRegistry)
              .observe(() -> ResponseEntity.ok(emp));
    }


    @GetMapping("/{id}")
    public ResponseEntity<String> getDetails(@PathVariable Long id) {
        String employeeName = employeeService.getEmployeeDetails(id);
        return Observation.createNotStarted("employee", observationRegistry)
                .observe(() -> ResponseEntity.ok(employeeName));
    }

    //pattern matching
    @GetMapping("/employee/name")
    public String getEmployeeName(@RequestParam("type") String type) {
        Object employeeObj;
        if ("valid".equals(type)) {
            Employee employee = new Employee();
            employee.setName("muskan");
            employeeObj = employee;
        } else {
            employeeObj = "Not an Employee";
        }
        return employeeService.getEmployeeName(employeeObj);
    }

   //sealed
    @GetMapping("/employee/{id}")
    public EmployeeResponse getEmployeeById(@PathVariable Long id) {
       return employeeService.getEmployeeById(id);
    }


    @PostMapping("/employee")
    public ResponseEntity<Employee> create(@RequestBody Employee employee){
        Employee createdEmployee = employeeService.createEmployee(employee);
        return new ResponseEntity<>(createdEmployee, HttpStatus.CREATED);
    }
}
