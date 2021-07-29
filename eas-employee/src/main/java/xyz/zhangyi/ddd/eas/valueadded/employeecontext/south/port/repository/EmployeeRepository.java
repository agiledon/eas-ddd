package xyz.zhangyi.ddd.eas.valueadded.employeecontext.south.port.repository;

import xyz.zhangyi.ddd.core.stereotype.Port;
import xyz.zhangyi.ddd.core.stereotype.PortType;
import xyz.zhangyi.ddd.eas.valueadded.employeecontext.domain.employee.Employee;

import java.util.Optional;

@Port(PortType.Repository)
public interface EmployeeRepository {
    Optional<Employee> latestEmployee();
}