package xyz.zhangyi.ddd.eas.trainingcontext.application.pl;

import xyz.zhangyi.ddd.eas.trainingcontext.domain.training.Training;

import java.io.Serializable;
import java.time.LocalDateTime;

public class TrainingResponse implements Serializable {
    private String trainingId;
    private String title;
    private String description;
    private LocalDateTime beginTime;
    private LocalDateTime endTime;
    private String place;

    public TrainingResponse(
            String trainingId,
            String title,
            String description,
            LocalDateTime beginTime,
            LocalDateTime endTime,
            String place) {
        this.trainingId = trainingId;
        this.title = title;
        this.description = description;
        this.beginTime = beginTime;
        this.endTime = endTime;
        this.place = place;
    }

    public static TrainingResponse from(Training training) {
        return new TrainingResponse(
                training.id().value(),
                training.title(),
                training.description(),
                training.beginTime(),
                training.endTime(),
                training.place());
    }

    public String getTrainingId() {
        return trainingId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getBeginTime() {
        return beginTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public String getPlace() {
        return place;
    }
}