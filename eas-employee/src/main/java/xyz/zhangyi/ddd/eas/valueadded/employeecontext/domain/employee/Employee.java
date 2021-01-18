package xyz.zhangyi.ddd.eas.valueadded.employeecontext.domain.employee;

import com.google.common.base.Strings;
import xyz.zhangyi.ddd.core.stereotype.Aggregate;
import xyz.zhangyi.ddd.eas.valueadded.employeecontext.domain.exception.InvalidEmployeeException;
import xyz.zhangyi.ddd.eas.valueadded.employeecontext.domain.exception.InvalidEmployeeIdException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@Aggregate
public class Employee {
    private static final int MAX_SEQUENCE_NO = 9999;
    private EmployeeId id;
    private final String name;
    private final IDCard idCard;
    private final Phone mobile;
    private final Gender gender;
    private final LocalDateTime onBoardingDate;

    public Employee(String name, IDCard idCard, Phone mobile) {
        this(name, idCard, mobile, LocalDateTime.now());
    }

    public Employee(String name, IDCard idCard, Phone mobile, LocalDateTime onBoardingDate) {
        this(null, name, idCard, mobile, onBoardingDate);
    }

    public Employee(EmployeeId id, String name, IDCard idCard, Phone mobile, LocalDateTime onBoardingDate) {
        this.id = id;
        this.name = validateName(name);
        this.idCard = requireNonNull(idCard, "ID Card should not be null");
        this.mobile = requireNonNull(mobile, "Mobile Phone should not be null");
        this.gender = idCard.isMale() ? Gender.Male : Gender.Female;
        this.onBoardingDate = onBoardingDate;
    }

    public EmployeeId id() {
        return this.id;
    }

    public boolean isMale() {
        return gender.isMale();
    }

    public boolean isFemale() {
        return gender.isFemale();
    }

    public LocalDateTime onBoardingDate() {
        return this.onBoardingDate;
    }

    private String validateName(String name) {
        if (Strings.isNullOrEmpty(name)) {
            throw new InvalidEmployeeException("Name should not be null or empty");
        }
        return name;
    }

    private <T> T requireNonNull(T obj, String errorMessage) {
        if (Objects.isNull(obj)) {
            throw new InvalidEmployeeException(errorMessage);
        }
        return obj;
    }

    public synchronized void assignIdFrom(String sequenceCode) {
        if (Strings.isNullOrEmpty(sequenceCode)) {
            throw new InvalidEmployeeIdException("Invalid sequence code.");
        }

        int sequenceNumber = parseSequenceNumber(sequenceCode) + 1;
        if (sequenceNumber > MAX_SEQUENCE_NO) {
            throw new InvalidEmployeeIdException("Invalid max value of sequence code.");
        }

        String currentSequenceCode = Strings.padStart(String.valueOf(sequenceNumber), 4, '0');
        String onBoardingDateCode = onBoardingDate.format(DateTimeFormatter.ofPattern("yyyyMMdd"));

        id = new EmployeeId(String.format("%s%s", onBoardingDateCode, currentSequenceCode));
    }

    private int parseSequenceNumber(String sequenceCode) {
        int sequenceNumber;
        try {
            sequenceNumber = Integer.parseInt(sequenceCode);
        } catch (NumberFormatException ex) {
            throw new InvalidEmployeeIdException("Invalid sequence code.");
        }
        return sequenceNumber;
    }
}
