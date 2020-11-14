package xyz.zhangyi.ddd.eas.valueadded.trainingcontext.domain.training;

import xyz.zhangyi.ddd.eas.valueadded.trainingcontext.domain.course.CourseId;

import java.time.LocalDateTime;
import java.util.UUID;

public class Training {
    private TrainingId id;
    private String title;
    private String description;
    private LocalDateTime beginTime;
    private LocalDateTime endTime;
    private String place;
    private CourseId courseId;

    public Training(String title, String description, LocalDateTime beginTime, LocalDateTime endTime, String place, CourseId courseId) {
        this(TrainingId.from(UUID.randomUUID().toString()), title, description, beginTime, endTime, place, courseId);
    }

    public Training(TrainingId id, String title, String description, LocalDateTime beginTime, LocalDateTime endTime, String place, CourseId courseId) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.beginTime = beginTime;
        this.endTime = endTime;
        this.place = place;
        this.courseId = courseId;
    }

    public TrainingId id() {
        return this.id;
    }

    public CourseId courseId() {
        return courseId;
    }

    public String title() {
        return this.title;
    }

    public String description() {
        return this.description;
    }

    public LocalDateTime beginTime() {
        return beginTime;
    }

    public LocalDateTime endTime() {
        return endTime;
    }

    public String place() {
        return place;
    }
}