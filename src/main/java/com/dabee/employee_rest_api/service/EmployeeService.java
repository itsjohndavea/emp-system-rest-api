package com.dabee.employee_rest_api.service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

import com.dabee.employee_rest_api.model.Employee;
import com.dabee.employee_rest_api.repository.EmployeeRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    // createa employee
    public Employee postEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    // read employee
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    // get ID employee
    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id).orElse(null);
    }

    // delete employee
    public void deleteEmployee(Long id) {
        if (!employeeRepository.existsById(id)) {
            throw new EntityNotFoundException("Employees with ID: " + id + " not found");
        }
        employeeRepository.deleteById(id);
    }

    // update employee
    public Employee updatEmployee(Long id, Employee employee) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        if (optionalEmployee.isPresent()) {
            Employee existingEmployee = optionalEmployee.get();

            existingEmployee.setName(employee.getName());
            existingEmployee.setEmail(employee.getEmail());
            existingEmployee.setPhone(employee.getPhone());
            existingEmployee.setDepartment(employee.getDepartment());

            return employeeRepository.save(existingEmployee);
        }
        return null;
    }
}
