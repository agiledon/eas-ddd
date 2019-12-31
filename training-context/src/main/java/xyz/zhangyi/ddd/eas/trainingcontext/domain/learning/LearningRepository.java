package xyz.zhangyi.ddd.eas.trainingcontext.domain.learning;

public interface LearningRepository {
    boolean exists(String candidateId, String courseId);
}