package xyz.zhangyi.ddd.eas.trainingcontext.domain.learning;

import xyz.zhangyi.ddd.eas.trainingcontext.domain.course.CourseId;

public interface LearningRepository {
    boolean exists(String traineeId, CourseId courseId);
}