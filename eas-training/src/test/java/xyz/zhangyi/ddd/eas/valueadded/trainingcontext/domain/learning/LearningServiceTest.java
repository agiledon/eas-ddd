package xyz.zhangyi.ddd.eas.valueadded.trainingcontext.domain.learning;

import org.junit.Before;
import org.junit.Test;
import xyz.zhangyi.ddd.eas.valueadded.trainingcontext.acl.port.repositories.LearningRepository;
import xyz.zhangyi.ddd.eas.valueadded.trainingcontext.domain.course.CourseId;
import xyz.zhangyi.ddd.eas.valueadded.trainingcontext.domain.training.Training;
import xyz.zhangyi.ddd.eas.valueadded.trainingcontext.domain.training.TrainingException;
import xyz.zhangyi.ddd.eas.valueadded.trainingcontext.domain.training.TrainingId;
import xyz.zhangyi.ddd.eas.valueadded.trainingcontext.acl.port.repositories.TrainingRepository;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

public class LearningServiceTest {
    private TrainingId trainingId;
    private String candidateId;
    private CourseId courseId;

    @Before
    public void setUp() throws Exception {
        trainingId = TrainingId.next();
        candidateId = "200901010010";
        courseId = CourseId.next();
    }

    @Test
    public void should_return_true_if_candidate_is_in_learn_list() {
        LocalDateTime beginTime = LocalDateTime.of(2020, 1, 8, 9, 0);
        LocalDateTime endTime = LocalDateTime.of(2020, 1, 9, 17, 0);
        Training training = new Training(trainingId, "ddd", "ddd training", beginTime, endTime, "London Room", courseId);

        TrainingRepository mockTrainingRepo = mock(TrainingRepository.class);
        when(mockTrainingRepo.trainingOf(trainingId)).thenReturn(Optional.of(training));

        LearningRepository mockLearningRepo = mock(LearningRepository.class);
        when(mockLearningRepo.exists(candidateId, training.courseId())).thenReturn(true);

        LearningService learningService = new LearningService();
        learningService.setTrainingRepository(mockTrainingRepo);
        learningService.setLearningRepository(mockLearningRepo);

        boolean beLearned = learningService.beLearned(candidateId, trainingId);

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

        assertThatThrownBy(() -> learningService.beLearned(candidateId, trainingId))
                .isInstanceOf(TrainingException.class)
        .hasMessageContaining(String.format("training by id {%s} can not be found", trainingId));

        verify(mockTrainingRepo).trainingOf(trainingId);
    }
}