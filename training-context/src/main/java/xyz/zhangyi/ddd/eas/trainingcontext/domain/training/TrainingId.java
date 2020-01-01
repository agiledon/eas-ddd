package xyz.zhangyi.ddd.eas.trainingcontext.domain.training;

import java.util.Objects;
import java.util.UUID;

public class TrainingId {
    private String id;

    public TrainingId(String id) {
        this.id = id;
    }

    public static TrainingId from(String id) {
        return new TrainingId(id);
    }

    public static TrainingId next() {
        return new TrainingId(UUID.randomUUID().toString());
    }

    public String value() {
        return this.id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TrainingId that = (TrainingId) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return id;
    }
}