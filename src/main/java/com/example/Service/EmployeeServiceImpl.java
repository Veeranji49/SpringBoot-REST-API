package com.example.Service;

import com.example.Entity.Employee;
import com.example.Repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    private EmployeeRepository employeeRepository;
    @Override
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Employee updateEmployee(Employee employee, long id) {
        Employee emp=employeeRepository.findById(id).get();
        emp.setName(employee.getName());
        emp.setSalary(employee.getSalary());
        emp.setAge(employee.getAge());
        emp.setEmail(employee.getEmail());
        emp.setLocation(employee.getLocation());
        emp.setIs_healthy(employee.getIs_healthy());
        return employeeRepository.save(emp);
    }

    @Override
    public void deleteEmployee(long id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public Employee getOneEmployee(long id) {
        return employeeRepository.findById(id).get();
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }
}
