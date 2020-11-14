package xyz.zhangyi.ddd.eas.valueadded.trainingcontext.ohs.local.appservices;

import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.zhangyi.ddd.eas.core.application.exceptions.ApplicationDomainException;
import xyz.zhangyi.ddd.eas.core.application.exceptions.ApplicationInfrastructureException;
import xyz.zhangyi.ddd.eas.core.application.exceptions.ApplicationValidationException;
import xyz.zhangyi.ddd.eas.core.domain.exceptions.DomainException;
import xyz.zhangyi.ddd.eas.valueadded.trainingcontext.ohs.local.pl.TrainingResponse;
import xyz.zhangyi.ddd.eas.valueadded.trainingcontext.domain.training.Training;
import xyz.zhangyi.ddd.eas.valueadded.trainingcontext.domain.training.TrainingId;
import xyz.zhangyi.ddd.eas.valueadded.trainingcontext.domain.training.TrainingService;

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