package xyz.zhangyi.ddd.eas.valueadded.trainingcontext.north.local.appservice;

import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.zhangyi.ddd.core.exception.ApplicationDomainException;
import xyz.zhangyi.ddd.core.exception.ApplicationInfrastructureException;
import xyz.zhangyi.ddd.core.exception.ApplicationValidationException;
import xyz.zhangyi.ddd.core.exception.DomainException;
import xyz.zhangyi.ddd.core.stereotype.Local;
import xyz.zhangyi.ddd.eas.valueadded.trainingcontext.north.message.TrainingResponse;
import xyz.zhangyi.ddd.eas.valueadded.trainingcontext.domain.training.Training;
import xyz.zhangyi.ddd.eas.valueadded.trainingcontext.domain.training.TrainingId;
import xyz.zhangyi.ddd.eas.valueadded.trainingcontext.domain.training.TrainingService;

@Service
@Local
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