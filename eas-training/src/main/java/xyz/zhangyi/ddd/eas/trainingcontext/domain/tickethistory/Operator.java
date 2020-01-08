package xyz.zhangyi.ddd.eas.trainingcontext.domain.tickethistory;

import java.util.Objects;

public class Operator {
    private String operatorId;
    private String name;

    public Operator(String operatorId, String name) {
        this.operatorId = operatorId;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Operator operator = (Operator) o;
        return operatorId.equals(operator.operatorId) &&
                name.equals(operator.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(operatorId, name);
    }
}