package com.woody_side.controller;

import com.woody_side.model.Employee;
import com.woody_side.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/v1/department")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping(path = "/{departmentId}/employee")
    public ResponseEntity<Void> saveEmployee(@PathVariable(value = "departmentId") Long departmentId,
                                       @RequestBody Employee employee) {
        employeeService.saveEmployee(departmentId, employee);

        return ResponseEntity.ok().build();
    }

    @GetMapping(path = "/{departmentId}/employee/{employeeId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Employee> findEmployeeById(@PathVariable(value = "departmentId") Long departmentId,
                                                     @PathVariable(value = "employeeId") Long employeeId) {
        return ResponseEntity.ok(employeeService.findEmployeeById(employeeId));
    }
}
