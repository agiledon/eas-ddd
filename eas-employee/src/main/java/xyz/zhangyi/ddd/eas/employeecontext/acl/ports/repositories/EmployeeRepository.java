package xyz.zhangyi.ddd.eas.employeecontext.acl.ports.repositories;

import xyz.zhangyi.ddd.eas.employeecontext.domain.Employee;

import java.util.Optional;

public interface EmployeeRepository {
    Optional<Employee> latestEmployee();
}