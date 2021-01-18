package xyz.zhangyi.ddd.eas.valueadded.employeecontext.domain.employee;

import org.junit.Test;
import xyz.zhangyi.ddd.eas.valueadded.employeecontext.acl.port.repository.EmployeeRepository;
import xyz.zhangyi.ddd.eas.valueadded.employeecontext.domain.employee.*;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class EmployeeIdGeneratorTest {

    private static final Employee latestEmp = new Employee(
            EmployeeId.from("201912240102"),
            "Zhang Yi",
            new IDCard("34052419800101001X"),
            new Phone("13013220101"),
            LocalDateTime.now());

    private static final Employee newEmp = new Employee(
            "Zhang Yi New",
            new IDCard("34052419800101001X"),
            new Phone("13013220101"),
            LocalDateTime.of(2020, 4, 2, 22, 10));

    @Test
    public void should_generate_next_employeeId_given_sequence_code() {
        EmployeeRepository mockEmpRepo = mock(EmployeeRepository.class);
        when(mockEmpRepo.latestEmployee()).thenReturn(Optional.of(latestEmp));

        EmployeeIdGenerator generator = new EmployeeIdGenerator();
        generator.setEmployeeRepository(mockEmpRepo);
        generator.generate(newEmp);

        assertThat(newEmp.id()).isEqualTo(EmployeeId.from("202004020103"));
        verify(mockEmpRepo).latestEmployee();
    }

    @Test
    public void should_generate_next_employeeId_begin_with_0001_given_no_employee_found() {
        EmployeeRepository mockEmpRepo = mock(EmployeeRepository.class);
        when(mockEmpRepo.latestEmployee()).thenReturn(Optional.empty());

        EmployeeIdGenerator generator = new EmployeeIdGenerator();
        generator.setEmployeeRepository(mockEmpRepo);
        generator.generate(newEmp);

        assertThat(newEmp.id()).isEqualTo(EmployeeId.from("202004020001"));
        verify(mockEmpRepo).latestEmployee();
    }
}
