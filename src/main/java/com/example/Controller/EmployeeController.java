package com.example.Controller;

import com.example.Entity.Employee;
import com.example.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PutMapping(value="/update/{id}")
    public String updatee(@RequestBody Employee employee, @PathVariable long id)
    {
        employeeService.updateEmployee(employee,id);
        return "Updated Successfully";
    }

    @DeleteMapping("/delete/{id}")
    public String deletee(@PathVariable long id)
    {
        employeeService.deleteEmployee(id);
        return "deleted successfully";
    }

    @GetMapping(value="/getone/{id}")
    public Employee getOne(@PathVariable long id)
    {
        return employeeService.getOneEmployee(id);
    }

    @GetMapping(value="/getall")
    public List<Employee> getall()
    {
        return employeeService.getAllEmployees();
    }
}
