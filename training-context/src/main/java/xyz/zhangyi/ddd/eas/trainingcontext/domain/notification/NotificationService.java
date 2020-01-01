package xyz.zhangyi.ddd.eas.trainingcontext.domain.notification;

import xyz.zhangyi.ddd.eas.trainingcontext.domain.ticket.Nominator;
import xyz.zhangyi.ddd.eas.trainingcontext.domain.ticket.Nominee;
import xyz.zhangyi.ddd.eas.trainingcontext.domain.ticket.Ticket;
import xyz.zhangyi.ddd.eas.trainingcontext.domain.training.Training;
import xyz.zhangyi.ddd.eas.trainingcontext.domain.training.TrainingException;
import xyz.zhangyi.ddd.eas.trainingcontext.domain.training.TrainingRepository;
import xyz.zhangyi.ddd.eas.trainingcontext.domain.validdate.ValidDate;
import xyz.zhangyi.ddd.eas.trainingcontext.domain.validdate.ValidDateException;
import xyz.zhangyi.ddd.eas.trainingcontext.domain.validdate.ValidDateRepository;
import xyz.zhangyi.ddd.eas.trainingcontext.domain.validdate.ValidDateType;
import xyz.zhangyi.ddd.eas.trainingcontext.gateway.interfaces.NotificationClient;

import java.util.Optional;

public class NotificationService {
    private MailTemplateRepository templateRepository;
    private NotificationClient notificationClient;
    private TrainingRepository trainingRepository;
    private ValidDateRepository validDateRepository;

    public void notifyNominee(Ticket ticket, Nominator nominator, Nominee nominee) {
        Optional<MailTemplate> optionalMailTemplate = templateRepository.templateOf(TemplateType.Nomination);
        if (!optionalMailTemplate.isPresent()) {
            throw new MailTemplateException(String.format("mail template by %s was not found.", TemplateType.Nomination));
        }
        MailTemplate mailTemplate = optionalMailTemplate.get();

        Optional<Training> optionalTraining = trainingRepository.trainingOf(ticket.trainingId());
        if (!optionalTraining.isPresent()) {
            throw new TrainingException(String.format("training by id {%s} was not found.", ticket.trainingId()));
        }

        Optional<ValidDate> optionalValidDate = validDateRepository.validDateOf(ticket.trainingId(), ValidDateType.PODeadline);
        if (!optionalValidDate.isPresent()) {
            throw new ValidDateException(String.format("valid date by training id {%s} was not found.", ticket.trainingId()));
        }

        Training training = optionalTraining.get();
        ValidDate validDate = optionalValidDate.get();

        VariableContext variableContext = buildVariableContext(ticket, nominator, nominee, training, validDate);
        Notification notification = mailTemplate.compose(variableContext);

        notificationClient.send(notification);
    }

    private VariableContext buildVariableContext(Ticket ticket, Nominator nominator, Nominee nominee, Training training, ValidDate validDate) {
        VariableContext variableContext = new VariableContext();
        variableContext.put("ticket", ticket);
        variableContext.put("training", training);
        variableContext.put("valid_date", validDate);
        variableContext.put("nominator", nominator);
        variableContext.put("nominee", nominee);
        return variableContext;
    }

    public void setMailTemplateRepository(MailTemplateRepository templateRepository) {
        this.templateRepository = templateRepository;
    }

    public void setNotificationClient(NotificationClient notificationClient) {
        this.notificationClient = notificationClient;
    }

    public void setTrainingRepository(TrainingRepository trainingRepository) {
        this.trainingRepository = trainingRepository;
    }

    public void setValidDateRepository(ValidDateRepository validDateRepository) {
        this.validDateRepository = validDateRepository;
    }
}