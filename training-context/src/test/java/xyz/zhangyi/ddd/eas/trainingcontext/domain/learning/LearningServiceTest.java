package xyz.zhangyi.ddd.eas.trainingcontext.domain.learning;

import org.junit.Before;
import org.junit.Test;
import xyz.zhangyi.ddd.eas.trainingcontext.domain.training.Training;
import xyz.zhangyi.ddd.eas.trainingcontext.domain.training.TrainingException;
import xyz.zhangyi.ddd.eas.trainingcontext.domain.training.TrainingId;
import xyz.zhangyi.ddd.eas.trainingcontext.domain.training.TrainingRepository;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

public class LearningServiceTest {
    private String trainingIdValue;
    private TrainingId trainingId;
    private String candidateId;
    private String courseId;

    @Before
    public void setUp() throws Exception {
        trainingIdValue = "111011111111";
        trainingId = TrainingId.from(trainingIdValue);
        candidateId = "200901010010";
        courseId = "111111111111";
    }

    @Test
    public void should_return_true_if_candidate_is_in_learn_list() {
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

    @Test
    public void should_throw_TrainingException_if_training_not_found() {
        TrainingRepository mockTrainingRepo = mock(TrainingRepository.class);
        when(mockTrainingRepo.trainingOf(trainingId)).thenReturn(Optional.empty());

        LearningRepository mockLearningRepo = mock(LearningRepository.class);

        LearningService learningService = new LearningService();
        learningService.setTrainingRepository(mockTrainingRepo);
        learningService.setLearningRepository(mockLearningRepo);

        assertThatThrownBy(() -> learningService.beLearned(candidateId, trainingIdValue))
                .isInstanceOf(TrainingException.class)
        .hasMessageContaining(String.format("training by id {%s} can not be found", trainingId));

        verify(mockTrainingRepo).trainingOf(trainingId);
    }
}