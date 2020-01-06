package xyz.zhangyi.ddd.eas.trainingcontext.application;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import xyz.zhangyi.ddd.eas.trainingcontext.application.messages.NominationRequest;
import xyz.zhangyi.ddd.eas.trainingcontext.domain.course.CourseId;
import xyz.zhangyi.ddd.eas.trainingcontext.domain.ticket.TrainingRole;
import xyz.zhangyi.ddd.eas.trainingcontext.domain.training.Training;
import xyz.zhangyi.ddd.eas.trainingcontext.domain.training.TrainingId;
import xyz.zhangyi.ddd.eas.trainingcontext.domain.training.TrainingRepository;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring-mybatis.xml")
public class NominationAppServiceIT {
    @Autowired
    private TrainingRepository trainingRepository;

//    @Autowired
//    private NominationAppService nominationAppService;

//    @Test
//    public void should_nominate_candidate_to_nominee() {
//        NominationRequest nominationRequest = createNominationRequest();
//
//        nominationAppService.nominate(nominationRequest);
//
//    }

    @Test
    public void test_trainingRepo() {
        Optional<Training> optionalTraining = trainingRepository.trainingOf(TrainingId.from("3070c3c9-f809-4a06-8fc4-ff7d330c86ca"));

        assertThat(optionalTraining.isPresent()).isTrue();
        Training training = optionalTraining.get();
        assertThat(training.place()).isEqualTo("Beijing Room");
        assertThat(training.courseId()).isEqualTo(CourseId.from("cc70c3c9-f809-4a06-8fc4-ff7d330c86ca"));
    }

    private NominationRequest createNominationRequest() {
        String ticketId = "tt70c3c9-f809-4a06-8fc4-ff7d330c86ca";
        String trainingId = "3070c3c9-f809-4a06-8fc4-ff7d330c86ca";
        String candidateId = "201905051000";
        String candidateName = "guo jin";
        String candidateEmail = "jin.guo@eas.com";
        String nominatorId = "201001010111";
        String nominatorName = "ma yu";
        String nominatorEmail = "yu.ma@eas.com";
        TrainingRole nominatorRole = TrainingRole.Coordinator;

        return new NominationRequest(
                ticketId,
                trainingId,
                candidateId,
                candidateName,
                candidateEmail,
                nominatorId,
                nominatorName,
                nominatorEmail,
                nominatorRole);
    }
}