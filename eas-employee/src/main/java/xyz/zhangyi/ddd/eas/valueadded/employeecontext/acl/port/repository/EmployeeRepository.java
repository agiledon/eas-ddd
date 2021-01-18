package xyz.zhangyi.ddd.eas.valueadded.employeecontext.acl.port.repository;

import xyz.zhangyi.ddd.eas.valueadded.employeecontext.domain.employee.Employee;

import java.util.Optional;

public interface EmployeeRepository {
    Optional<Employee> latestEmployee();
}