package com.example.Controller;

import com.example.Entity.Employee;
import com.example.Service.EmployeeService;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value="/api/v1/employee")
//@Api(tags = "Employee Controller", description = "To checking APIs")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    @GetMapping(value="/msg")
    //@ApiOperation("Get a greeting message")
    public String msg()
    {
        return "Welcome to SpringBoot REST API Application";
    }

    @PostMapping(value="/save")
    //@ApiOperation("To posting the employee details")
    public String savee(@Valid @RequestBody Employee employee)
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
    //@ApiOperation("To updating the employee details")
    public String updatee(@Valid @RequestBody Employee employee, @PathVariable long id)
    {
        employeeService.updateEmployee(employee,id);
        logger.error("failed in updation");
        return "Updated Successfully";
    }

    @DeleteMapping("/delete/{id}")
    //@ApiOperation("To deleting the employee details")
    public String deletee(@PathVariable long id)
    {
        employeeService.deleteEmployee(id);
        logger.error("failed in deletion");
        return "deleted successfully";
    }

    @GetMapping(value="/getone/{id}")
    //@ApiOperation("getting the one employee details")
    public Employee getOne(@PathVariable long id)
    {
        return employeeService.getOneEmployee(id);
    }

    @GetMapping(value="/getall")
    //@ApiOperation("Getting all the employee details")
    public List<Employee> getall()
    {
        return employeeService.getAllEmployees();
    }
    @GetMapping(value="/pages")
    public ResponseEntity<Page<Employee>> getEmployees(
            @RequestParam(defaultValue = "name") String sortBy,
            @RequestParam(defaultValue = "asc") String sortOrder,
            Pageable pageable) {

        // Construct Sort object based on sorting parameters
        Sort sort = Sort.by(sortOrder.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC, sortBy);

        // Apply sorting to pageable
        pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);

        // Retrieve paginated and sorted data
        Page<Employee> employees = employeeService.findAll(pageable);

        return ResponseEntity.ok().body(employees);
    }
}
