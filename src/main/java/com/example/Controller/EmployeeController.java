package com.example.Controller;

import com.example.Entity.Employee;
import com.example.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/api/v1/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping(value="/save")
    public String savee(@RequestBody Employee employee)
    {
        Employee emp=employeeService.saveEmployee(employee);
        String msg=null;
        if(null!=emp)
        {
            msg="Successfully saved";
        }
        else {
            msg="failed in insertion";
        }
        return msg;
    }
}
