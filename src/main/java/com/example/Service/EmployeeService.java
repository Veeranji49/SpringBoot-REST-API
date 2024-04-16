package com.example.Service;

import com.example.Entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EmployeeService {

    public Employee saveEmployee(Employee employee);

    public Employee updateEmployee(Employee employee, long id);

    public void deleteEmployee(long id);

    public Employee getOneEmployee(long id);

    public List<Employee> getAllEmployees();

    public Page<Employee> findAll(Pageable pageable);

}
