package xyz.zhangyi.ddd.eas.valueadded.trainingcontext.domain.ticket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.zhangyi.ddd.eas.valueadded.trainingcontext.domain.candidate.Candidate;
import xyz.zhangyi.ddd.eas.valueadded.trainingcontext.domain.exception.NominationException;
import xyz.zhangyi.ddd.eas.valueadded.trainingcontext.domain.learning.LearningService;
import xyz.zhangyi.ddd.eas.valueadded.trainingcontext.domain.notification.NotificationService;
import xyz.zhangyi.ddd.eas.valueadded.trainingcontext.domain.training.TrainingId;

@Service
public class NominationService {
    @Autowired
    private LearningService learningService;
    @Autowired
    private TicketService ticketService;
    @Autowired
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