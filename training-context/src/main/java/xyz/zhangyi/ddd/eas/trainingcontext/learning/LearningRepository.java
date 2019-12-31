package xyz.zhangyi.ddd.eas.trainingcontext.learning;

public interface LearningRepository {
    boolean exists(String candidateId, String courseId);
}