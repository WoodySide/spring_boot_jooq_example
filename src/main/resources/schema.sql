DROP TABLE IF EXISTS department;

CREATE TABLE department (
    department_id BIGSERIAL PRIMARY KEY NOT NULL,
    department_name VARCHAR(30) NOT NULL,
    min_salary DECIMAL NOT NULL ,
    max_salary DECIMAL NOT NULL
);

DROP TABLE IF EXISTS employee;

CREATE TABLE employee (
    employee_id BIGSERIAL PRIMARY KEY NOT NULL,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(150) NOT NULL,
    salary DECIMAL NOT NULL,
    department_id BIGSERIAL NOT NULL,
    FOREIGN KEY (department_id)
    REFERENCES department (department_id)
);