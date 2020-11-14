package xyz.zhangyi.ddd.eas.valueadded.trainingcontext.domain.course;

import xyz.zhangyi.ddd.eas.core.domain.AbstractIdentity;

import java.util.UUID;

public class CourseId extends AbstractIdentity<String> {
    private String value;

    protected CourseId(String value) {
        super(value);
    }

    public static CourseId from(String value) {
        return new CourseId(value);
    }

    public static CourseId next() {
        return new CourseId(UUID.randomUUID().toString());
    }
}
