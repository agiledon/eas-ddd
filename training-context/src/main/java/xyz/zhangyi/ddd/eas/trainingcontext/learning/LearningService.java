package xyz.zhangyi.ddd.eas.trainingcontext.learning;

import xyz.zhangyi.ddd.eas.trainingcontext.training.Training;
import xyz.zhangyi.ddd.eas.trainingcontext.training.TrainingException;
import xyz.zhangyi.ddd.eas.trainingcontext.training.TrainingId;
import xyz.zhangyi.ddd.eas.trainingcontext.training.TrainingRepository;

import java.util.Optional;

public class LearningService {
    private TrainingRepository trainingRepo;
    private LearningRepository learningRepo;

    public void setTrainingRepository(TrainingRepository trainingRepo) {
        this.trainingRepo = trainingRepo;
    }

    public void setLearningRepository(LearningRepository learningRepo) {
        this.learningRepo = learningRepo;
    }

    public boolean beLearned(String candidateId, String trainingId) {
        Optional<Training> optionalTraining = trainingRepo.trainingOf(TrainingId.from(trainingId));
        if (!optionalTraining.isPresent())
            throw new TrainingException(String.format("training by id {%} can not be found.", trainingId));

        Training training = optionalTraining.get();
        return learningRepo.exists(candidateId, training.courseId());
    }
}