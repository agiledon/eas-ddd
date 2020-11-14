package xyz.zhangyi.ddd.eas.valueadded.trainingcontext.ohs.local.pl;

import org.junit.Test;
import xyz.zhangyi.ddd.eas.valueadded.trainingcontext.domain.candidate.Candidate;
import xyz.zhangyi.ddd.eas.valueadded.trainingcontext.domain.ticket.Nominator;
import xyz.zhangyi.ddd.eas.valueadded.trainingcontext.domain.ticket.TrainingRole;
import xyz.zhangyi.ddd.eas.valueadded.trainingcontext.domain.training.TrainingId;

import static org.assertj.core.api.Assertions.assertThat;

public class NominatingCandidateRequestTest {
    private String ticketId;
    private String trainingId;
    private String candidateId;
    private String candidateName;
    private String candidateEmail;
    private String nominatorId;
    private String nominatorName;
    private String nominatorEmail;
    private TrainingRole nominatorRole;
    private NominatingCandidateRequest nominatingCandidateRequest;

    @Test
    public void should_compose_Candidate() {
        nominatingCandidateRequest = createNominationRequest();

        Candidate candidate = nominatingCandidateRequest.toCandidate();

        assertThat(candidate.employeeId()).isEqualTo(candidateId);
        assertThat(candidate.name()).isEqualTo(candidateName);
        assertThat(candidate.email()).isEqualTo(candidateEmail);
        assertThat(candidate.trainingId()).isEqualTo(TrainingId.from(trainingId));
    }

    @Test
    public void should_compose_Nominator() {
        nominatingCandidateRequest = createNominationRequest();
        Nominator expectedNominator = new Nominator(nominatorId, nominatorName, nominatorEmail, TrainingRole.Coordinator);

        Nominator nominator = nominatingCandidateRequest.toNominator();

        assertThat(nominator).isEqualTo(expectedNominator);
    }

    private NominatingCandidateRequest createNominationRequest() {
        ticketId = "3070c3c9-f809-4a06-8fc4-ff7d330c86ca";
        trainingId = "tt70c3c9-f809-4a06-8fc4-ff7d330c86ca";
        candidateId = "201905051000";
        candidateName = "guo jin";
        candidateEmail = "jin.guo@eas.com";
        nominatorId = "201001010111";
        nominatorName = "ma yu";
        nominatorEmail = "yu.ma@eas.com";
        nominatorRole = TrainingRole.Coordinator;

        return new NominatingCandidateRequest(
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