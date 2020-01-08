package xyz.zhangyi.ddd.eas.trainingcontext.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.zhangyi.ddd.eas.core.application.ApplicationException;
import xyz.zhangyi.ddd.eas.core.domain.DomainException;
import xyz.zhangyi.ddd.eas.trainingcontext.application.pl.TrainingResponse;
import xyz.zhangyi.ddd.eas.trainingcontext.domain.training.Training;
import xyz.zhangyi.ddd.eas.trainingcontext.domain.training.TrainingId;
import xyz.zhangyi.ddd.eas.trainingcontext.domain.training.TrainingService;

@Service
public class TrainingAppService {
    @Autowired
    private TrainingService trainingService;

    public TrainingResponse trainingOf(String trainingId) throws ApplicationException {
        try {
            Training training = trainingService.trainingOf(TrainingId.from(trainingId));
            // todo: fetch the course detail by training.courseId() by CourseService;
            return TrainingResponse.from(training);
        } catch (DomainException ex) {
            throw new ApplicationException(ex.getMessage(), ex);
        }
    }
}