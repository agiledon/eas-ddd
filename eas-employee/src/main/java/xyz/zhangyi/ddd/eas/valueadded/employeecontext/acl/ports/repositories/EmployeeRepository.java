package xyz.zhangyi.ddd.eas.valueadded.employeecontext.acl.ports.repositories;

import xyz.zhangyi.ddd.eas.valueadded.employeecontext.domain.Employee;

import java.util.Optional;

public interface EmployeeRepository {
    Optional<Employee> latestEmployee();
}