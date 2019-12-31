package xyz.zhangyi.ddd.eas.trainingcontext.learning;

import org.junit.Test;
import xyz.zhangyi.ddd.eas.trainingcontext.training.Training;
import xyz.zhangyi.ddd.eas.trainingcontext.training.TrainingId;
import xyz.zhangyi.ddd.eas.trainingcontext.training.TrainingRepository;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class LearningServiceTest {
    @Test
    public void should_return_true_if_candidate_is_in_learn_list() {
        String trainingIdValue = "111011111111";
        TrainingId trainingId = TrainingId.from(trainingIdValue);
        String candidateId = "200901010010";
        String courseId = "111111111111";
        Training training = new Training(courseId);

        TrainingRepository mockTrainingRepo = mock(TrainingRepository.class);
        when(mockTrainingRepo.trainingOf(trainingId)).thenReturn(Optional.of(training));

        LearningRepository mockLearningRepo = mock(LearningRepository.class);
        when(mockLearningRepo.exists(candidateId, training.courseId())).thenReturn(true);

        LearningService learningService = new LearningService();
        learningService.setTrainingRepository(mockTrainingRepo);
        learningService.setLearningRepository(mockLearningRepo);

        boolean beLearned = learningService.beLearned(candidateId, trainingIdValue);

        assertThat(beLearned).isTrue();
        verify(mockTrainingRepo).trainingOf(trainingId);
        verify(mockLearningRepo).exists(candidateId, training.courseId());
    }
}