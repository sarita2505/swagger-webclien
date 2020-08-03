package com.spring.controller;

import com.spring.model.Employee;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employees/")
public class EmployeeController {
    @GetMapping()
    public ResponseEntity<Employee> getEmp(){
        Employee employee=new Employee();
        employee.setId(1);
        employee.setName("harish");
        System.out.println("server log**********");

        int i = 0;
        while(i >= 4)
        {
            i = i + 1;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(employee);
    }
}
