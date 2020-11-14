package xyz.zhangyi.ddd.eas.valueadded.trainingcontext.ohs.local.pl;

import xyz.zhangyi.ddd.eas.valueadded.trainingcontext.domain.candidate.Candidate;
import xyz.zhangyi.ddd.eas.valueadded.trainingcontext.domain.ticket.Nominator;
import xyz.zhangyi.ddd.eas.valueadded.trainingcontext.domain.ticket.TicketId;
import xyz.zhangyi.ddd.eas.valueadded.trainingcontext.domain.ticket.TrainingRole;
import xyz.zhangyi.ddd.eas.valueadded.trainingcontext.domain.training.TrainingId;

import java.io.Serializable;

// Request Message: DTO
public class NominatingCandidateRequest implements Serializable {
    private String ticketId;
    private String trainingId;
    private String candidateId;
    private String candidateName;
    private String candidateEmail;
    private String nominatorId;
    private String nominatorName;
    private String nominatorEmail;
    private TrainingRole nominatorRole;

    public NominatingCandidateRequest() {
    }

    public NominatingCandidateRequest(String ticketId,
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

    public TicketId getTicketId() {
        return TicketId.from(this.ticketId);
    }

    public TrainingId getTrainingId() {
        return TrainingId.from(this.trainingId);
    }

    public String getCandidateId() {
        return candidateId;
    }

    public String getCandidateName() {
        return candidateName;
    }

    public String getCandidateEmail() {
        return candidateEmail;
    }

    public String getNominatorId() {
        return nominatorId;
    }

    public String getNominatorName() {
        return nominatorName;
    }

    public String getNominatorEmail() {
        return nominatorEmail;
    }

    public TrainingRole getNominatorRole() {
        return nominatorRole;
    }

    public Candidate toCandidate() {
        return new Candidate(candidateId, candidateName, candidateEmail, TrainingId.from(trainingId));
    }

    public Nominator toNominator() {
        return new Nominator(nominatorId, nominatorName, nominatorEmail, nominatorRole);
    }
}