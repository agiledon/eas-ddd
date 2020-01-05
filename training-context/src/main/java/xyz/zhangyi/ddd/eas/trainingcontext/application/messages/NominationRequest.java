package xyz.zhangyi.ddd.eas.trainingcontext.application.messages;

import xyz.zhangyi.ddd.eas.trainingcontext.domain.candidate.Candidate;
import xyz.zhangyi.ddd.eas.trainingcontext.domain.ticket.Nominator;
import xyz.zhangyi.ddd.eas.trainingcontext.domain.ticket.TrainingRole;
import xyz.zhangyi.ddd.eas.trainingcontext.domain.training.TrainingId;

// Request Message: DTO
public class NominationRequest {
    private String ticketId;
    private String trainingId;
    private String candidateId;
    private String candidateName;
    private String candidateEmail;
    private String nominatorId;
    private String nominatorName;
    private String nominatorEmail;
    private TrainingRole nominatorRole;

    public NominationRequest(String ticketId,
                             String trainingId,
                             String candidateId,
                             String candidateName,
                             String candidateEmail,
                             String nominatorId,
                             String nominatorName,
                             String nominatorEmail,
                             TrainingRole nominatorRole) {
        this.ticketId = ticketId;
        this.trainingId = trainingId;
        this.candidateId = candidateId;
        this.candidateName = candidateName;
        this.candidateEmail = candidateEmail;
        this.nominatorId = nominatorId;
        this.nominatorName = nominatorName;
        this.nominatorEmail = nominatorEmail;
        this.nominatorRole = nominatorRole;
    }

    public Candidate toCandidate() {
        return new Candidate(candidateId, candidateName, candidateEmail, TrainingId.from(trainingId));
    }

    public Nominator toNominator() {
        return new Nominator(nominatorId, nominatorName, nominatorEmail, nominatorRole);
    }
}