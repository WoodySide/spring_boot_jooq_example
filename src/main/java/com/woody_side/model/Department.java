package com.woody_side.model;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Department {
    private Long id;
    private String name;
    private BigDecimal minSalary;
    private BigDecimal maxSalary;
    private List<Employee> employees;
}
