package xyz.zhangyi.ddd.eas.trainingcontext.domain.course;

import java.util.UUID;

public class CourseId {
    private String value;

    private CourseId(String value) {
        this.value = value;
    }

    public static CourseId next() {
        return new CourseId(UUID.randomUUID().toString());
    }
}
