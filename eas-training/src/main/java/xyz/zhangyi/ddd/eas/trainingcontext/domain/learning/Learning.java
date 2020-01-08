package xyz.zhangyi.ddd.eas.trainingcontext.domain.learning;

import xyz.zhangyi.ddd.eas.trainingcontext.domain.course.CourseId;
import xyz.zhangyi.ddd.eas.trainingcontext.domain.training.TrainingId;

public class Learning {
    private String learningId;
    private CourseId courseId;
    private TrainingId trainingId;
    private String traineeId;

    public Learning(String learningId, CourseId courseId, TrainingId trainingId, String traineeId) {
        this.learningId = learningId;
        this.courseId = courseId;
        this.traineeId = traineeId;
        this.traineeId = traineeId;
    }
}