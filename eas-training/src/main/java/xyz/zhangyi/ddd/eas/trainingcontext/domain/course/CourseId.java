package xyz.zhangyi.ddd.eas.trainingcontext.domain.course;

import xyz.zhangyi.ddd.eas.core.domain.Identity;

import java.util.Objects;
import java.util.UUID;

public class CourseId implements Identity {
    private String value;

    private CourseId(String value) {
        this.value = value;
    }

    public static CourseId from(String value) {
        return new CourseId(value);
    }

    public static CourseId next() {
        return new CourseId(UUID.randomUUID().toString());
    }

    @Override
    public String value() {
        return this.value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CourseId courseId = (CourseId) o;
        return value.equals(courseId.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
