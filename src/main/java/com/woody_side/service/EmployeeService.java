package com.woody_side.service;

import com.woody_side.model.Employee;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import static com.woody_side.model.tables.Employee.EMPLOYEE;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final DepartmentService departmentService;
    private final DSLContext dslContext;
    private final ModelMapper mapper;

    public void saveEmployee(Long departmentId, Employee employee) {
        var department = departmentService.findDepartmentById(departmentId);

        dslContext.insertInto(EMPLOYEE, EMPLOYEE.EMPLOYEE_ID, EMPLOYEE.FIRST_NAME,
                EMPLOYEE.LAST_NAME, EMPLOYEE.SALARY, EMPLOYEE.DEPARTMENT_ID)
                .values(employee.getId(), employee.getFirstName(),
                        employee.getLastName(), employee.getSalary(), Math.toIntExact(department.getId()))
                .execute();
    }

    public Employee findEmployeeById(Long employeeId) {
        return dslContext
                .selectFrom(EMPLOYEE)
                .where(EMPLOYEE.EMPLOYEE_ID.eq(employeeId))
                .fetchOptional()
                .map(emp -> mapper.map(emp, Employee.class))
                .orElse(null);
    }
}
