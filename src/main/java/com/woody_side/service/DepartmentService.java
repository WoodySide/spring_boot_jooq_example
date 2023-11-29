package com.woody_side.service;

import com.woody_side.model.Department;
import com.woody_side.model.Tables;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.woody_side.model.tables.Department.DEPARTMENT;

@Service
@RequiredArgsConstructor
public class DepartmentService {

    private final DSLContext dslContext;
    private final ModelMapper mapper;

    public void saveDepartment(Department department) {
        dslContext.insertInto(DEPARTMENT, DEPARTMENT.DEPARTMENT_ID,
                DEPARTMENT.DEPARTMENT_NAME, DEPARTMENT.MAX_SALARY, DEPARTMENT.MIN_SALARY)
                .values(department.getId(), department.getName(), department.getMaxSalary(), department.getMinSalary())
                .execute();
    }

    public List<Department> findAllDepartments() {
        return dslContext
                .selectFrom(DEPARTMENT)
                .fetch()
                .stream()
                .map(dep -> mapper.map(dep, Department.class))
                .toList();
    }

    public Department findDepartmentById(Long departmentId) {
        return dslContext
                .selectFrom(DEPARTMENT)
                .where(Tables.DEPARTMENT.DEPARTMENT_ID.eq(departmentId))
                .fetchOptional()
                .map(dep -> mapper.map(dep, Department.class))
                .orElse(null);
    }

    public void deleteDepartmentById(Long departmentId) {
        dslContext
                .deleteFrom(DEPARTMENT)
                .where(Tables.DEPARTMENT.DEPARTMENT_ID.eq(departmentId))
                .execute();
    }

    public void updateDepartment(Department department) {
        dslContext
                .update(Tables.DEPARTMENT)
                .set(DEPARTMENT.DEPARTMENT_NAME, department.getName())
                .set(DEPARTMENT.MAX_SALARY, department.getMaxSalary())
                .set(DEPARTMENT.MIN_SALARY, department.getMinSalary())
                .where(DEPARTMENT.DEPARTMENT_ID.eq(department.getId()))
                .execute();
    }
}
