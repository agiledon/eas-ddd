package xyz.zhangyi.ddd.eas.valueadded.trainingcontext.acl.port.repositories;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import xyz.zhangyi.ddd.eas.valueadded.trainingcontext.domain.course.CourseId;

@Mapper
@Repository
public interface LearningRepository {
    boolean exists(String traineeId, CourseId courseId);
}