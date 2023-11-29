package com.woody_side.controller;

import com.woody_side.model.Department;
import com.woody_side.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/department")
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentService departmentService;

    @PostMapping(value = "/")
    public ResponseEntity<Void> insertDepartment(@RequestBody Department department) {
        departmentService.saveDepartment(department);

        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/")
    public ResponseEntity<List<Department>> findAllDepartments() {
        return ResponseEntity.ok(departmentService.findAllDepartments());
    }

    @GetMapping(value = "/{departmentId}")
    public ResponseEntity<Department> findDepartmentById(@PathVariable(value = "departmentId") Long departmentId) {
        return ResponseEntity.ok().body(departmentService.findDepartmentById(departmentId));
    }

    @DeleteMapping(value = "/{departmentId}")
    public ResponseEntity<Void> deleteDepartmentById(@PathVariable(value = "departmentId") Long departmentId) {
        departmentService.deleteDepartmentById(departmentId);

        return ResponseEntity.ok().build();
    }

    @PutMapping(value = "/")
    public ResponseEntity<Void> updateDepartment(@RequestBody Department department) {
        departmentService.updateDepartment(department);

        return ResponseEntity.ok().build();
    }
}
