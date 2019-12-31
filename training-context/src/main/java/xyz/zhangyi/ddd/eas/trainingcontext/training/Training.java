package xyz.zhangyi.ddd.eas.trainingcontext.training;

public class Training {
    private String courseId;

    public Training(String courseId) {
        this.courseId = courseId;
    }

    public String courseId() {
        return courseId;
    }
}