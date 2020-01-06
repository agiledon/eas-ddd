package xyz.zhangyi.ddd.eas.trainingcontext.domain.learning;

import org.springframework.stereotype.Repository;
import xyz.zhangyi.ddd.eas.trainingcontext.domain.course.CourseId;

@Repository
public interface LearningRepository {
    boolean exists(String traineeId, CourseId courseId);
}