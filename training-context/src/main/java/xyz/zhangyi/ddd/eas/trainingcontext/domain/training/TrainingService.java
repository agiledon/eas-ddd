package xyz.zhangyi.ddd.eas.trainingcontext.domain.training;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class TrainingService {
    @Autowired
    private TrainingRepository trainingRepository;

    public Training trainingOf(TrainingId trainingId) {
        Optional<Training> optionalTraining = trainingRepository.trainingOf(trainingId);
        return optionalTraining.orElseThrow(() -> trainingNotFoundError(trainingId));
    }

    private TrainingException trainingNotFoundError(TrainingId trainingId) {
        return new TrainingException(String.format("Training by id {%s} is not found.", trainingId));
    }

    public void setTrainingRepository(TrainingRepository trainingRepo) {
        trainingRepository = trainingRepo;
    }
}