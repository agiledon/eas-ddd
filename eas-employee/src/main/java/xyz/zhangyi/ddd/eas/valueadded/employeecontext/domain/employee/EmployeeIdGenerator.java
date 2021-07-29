package xyz.zhangyi.ddd.eas.valueadded.employeecontext.domain.employee;

import xyz.zhangyi.ddd.eas.valueadded.employeecontext.south.port.repository.EmployeeRepository;

public class EmployeeIdGenerator {
    public static final String START_SEQUENCE_NO = "0000";
    private EmployeeRepository empRepo;

    public void generate(Employee employee) {
        String sequenceNo = empRepo.latestEmployee().map(e -> e.id().sequenceNo()).orElse(START_SEQUENCE_NO);
        employee.assignIdFrom(sequenceNo);
    }

    public void setEmployeeRepository(EmployeeRepository empRepo) {
        this.empRepo = empRepo;
    }
}