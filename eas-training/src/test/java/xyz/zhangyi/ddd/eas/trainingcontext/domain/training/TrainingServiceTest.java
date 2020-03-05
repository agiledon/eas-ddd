package xyz.zhangyi.ddd.eas.trainingcontext.domain.training;

import org.junit.Test;
import xyz.zhangyi.ddd.eas.trainingcontext.acl.ports.repositories.TrainingRepository;
import xyz.zhangyi.ddd.eas.trainingcontext.domain.course.CourseId;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TrainingServiceTest {

    private final TrainingId trainingId = TrainingId.next();

    @Test
    public void should_get_training_by_training_id() {
        TrainingRepository mockTrainingRepo = mock(TrainingRepository.class);
        when(mockTrainingRepo.trainingOf(trainingId)).thenReturn(Optional.of(createTraining()));

        TrainingService trainingService = new TrainingService();
        trainingService.setTrainingRepository(mockTrainingRepo);

        Training actualTraining = trainingService.trainingOf(trainingId);

        assertThat(actualTraining).isNotNull();
        assertThat(actualTraining.title()).isEqualTo("DDD");
    }

    @Test
    public void should_throw_TrainingException_if_not_found() {
        TrainingRepository mockTrainingRepo = mock(TrainingRepository.class);
        when(mockTrainingRepo.trainingOf(trainingId)).thenReturn(Optional.empty());

        TrainingService trainingService = new TrainingService();
        trainingService.setTrainingRepository(mockTrainingRepo);

        assertThatThrownBy(() -> trainingService.trainingOf(trainingId))
                .isInstanceOf(TrainingException.class)
                .hasMessageContaining(String.format("Training by id {%s} is not found", trainingId));
    }

    private Training createTraining() {
        LocalDateTime beginTime = LocalDateTime.of(2020, 5, 20, 9, 0, 0);
        LocalDateTime endTime = LocalDateTime.of(2020, 5, 21, 17, 0, 0);
        CourseId courseId = CourseId.next();
        return new Training(trainingId, "DDD", "DDD Description", beginTime, endTime, "Beijing Room", courseId);
    }
}