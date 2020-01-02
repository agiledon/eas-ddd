package xyz.zhangyi.ddd.eas.trainingcontext.domain.ticket;

import xyz.zhangyi.ddd.eas.trainingcontext.domain.candidate.Candidate;
import xyz.zhangyi.ddd.eas.trainingcontext.domain.exceptions.NominationException;
import xyz.zhangyi.ddd.eas.trainingcontext.domain.learning.LearningService;
import xyz.zhangyi.ddd.eas.trainingcontext.domain.notification.NotificationService;
import xyz.zhangyi.ddd.eas.trainingcontext.domain.training.TrainingId;

public class NominationService {
    private LearningService learningService;
    private TicketService ticketService;
    private NotificationService notificationService;

    public void setLearningService(LearningService learningService) {
        this.learningService = learningService;
    }

    public void setTicketService(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    public void setNotificationService(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    public void nominate(TicketId ticketId, TrainingId trainingId, Candidate candidate, Nominator nominator) {
        boolean beLearned = learningService.beLearned(candidate.employeeId(), trainingId);
        if (beLearned) {
            throw new NominationException(String.format("can not nominate the candidate %s who had learned in the training", candidate.name()));
        }
        Ticket ticket = ticketService.nominate(ticketId, nominator, candidate);
        notificationService.notifyNominee(ticket, nominator, candidate.toNominee());
    }
}