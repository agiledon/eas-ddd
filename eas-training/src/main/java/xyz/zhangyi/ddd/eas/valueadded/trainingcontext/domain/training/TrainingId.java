package xyz.zhangyi.ddd.eas.valueadded.trainingcontext.domain.training;

import xyz.zhangyi.ddd.eas.core.domain.Identity;

import java.util.Objects;
import java.util.UUID;

public class TrainingId implements Identity {
    private String value;

    public TrainingId(String value) {
        this.value = value;
    }

    public static TrainingId from(String value) {
        return new TrainingId(value);
    }

    public static TrainingId next() {
        return new TrainingId(UUID.randomUUID().toString());
    }

    @Override
    public String value() {
        return this.value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TrainingId that = (TrainingId) o;
        return value.equals(that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return value;
    }
}