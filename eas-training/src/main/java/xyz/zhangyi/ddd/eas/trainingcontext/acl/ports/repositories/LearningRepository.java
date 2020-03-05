package xyz.zhangyi.ddd.eas.trainingcontext.acl.ports.repositories;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import xyz.zhangyi.ddd.eas.trainingcontext.domain.course.CourseId;

@Mapper
@Repository
public interface LearningRepository {
    boolean exists(String traineeId, CourseId courseId);
}