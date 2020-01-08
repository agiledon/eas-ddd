package xyz.zhangyi.ddd.eas.trainingcontext.application;

import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.zhangyi.ddd.eas.core.application.ApplicationDomainException;
import xyz.zhangyi.ddd.eas.core.application.ApplicationException;
import xyz.zhangyi.ddd.eas.core.application.ApplicationInfrastructureException;
import xyz.zhangyi.ddd.eas.core.application.ApplicationValidationException;
import xyz.zhangyi.ddd.eas.core.domain.DomainException;
import xyz.zhangyi.ddd.eas.trainingcontext.application.pl.TrainingResponse;
import xyz.zhangyi.ddd.eas.trainingcontext.domain.training.Training;
import xyz.zhangyi.ddd.eas.trainingcontext.domain.training.TrainingId;
import xyz.zhangyi.ddd.eas.trainingcontext.domain.training.TrainingService;

@Service
public class TrainingAppService {
    @Autowired
    private TrainingService trainingService;

    public TrainingResponse trainingOf(String trainingId) {
        if (Strings.isNullOrEmpty(trainingId)) {
            throw new ApplicationValidationException("TrainingId can not be null or empty");
        }
        try {
            Training training = trainingService.trainingOf(TrainingId.from(trainingId));
            // todo: fetch the course detail by training.courseId() by CourseService;
            return TrainingResponse.from(training);
        } catch (DomainException ex) {
            throw new ApplicationDomainException(ex.getMessage(), ex);
        } catch (Exception ex) {
            throw new ApplicationInfrastructureException(ex.getMessage(), ex);
        }
    }
}