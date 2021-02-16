package xyz.zhangyi.ddd.eas.valueadded.trainingcontext.domain.learning;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.zhangyi.ddd.core.stereotype.DomainService;
import xyz.zhangyi.ddd.eas.valueadded.trainingcontext.southbound.port.repository.LearningRepository;
import xyz.zhangyi.ddd.eas.valueadded.trainingcontext.domain.training.Training;
import xyz.zhangyi.ddd.eas.valueadded.trainingcontext.domain.training.TrainingException;
import xyz.zhangyi.ddd.eas.valueadded.trainingcontext.domain.training.TrainingId;
import xyz.zhangyi.ddd.eas.valueadded.trainingcontext.southbound.port.repository.TrainingRepository;

import java.util.Optional;

@Service
@DomainService
public class LearningService {
    @Autowired
    private TrainingRepository trainingRepo;
    @Autowired
    private LearningRepository learningRepo;

    public void setTrainingRepository(TrainingRepository trainingRepo) {
        this.trainingRepo = trainingRepo;
    }

    public void setLearningRepository(LearningRepository learningRepo) {
        this.learningRepo = learningRepo;
    }

    public boolean beLearned(String traineeId, TrainingId trainingId) {
        Optional<Training> optionalTraining = trainingRepo.trainingOf(trainingId);
        if (!optionalTraining.isPresent())
            throw new TrainingException(String.format("training by id {%s} can not be found.", trainingId));

        Training training = optionalTraining.get();
        return learningRepo.exists(traineeId, training.courseId());
    }
}