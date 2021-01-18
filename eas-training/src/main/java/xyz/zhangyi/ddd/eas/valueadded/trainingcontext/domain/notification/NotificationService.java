package xyz.zhangyi.ddd.eas.valueadded.trainingcontext.domain.notification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.zhangyi.ddd.eas.valueadded.trainingcontext.acl.port.repositories.MailTemplateRepository;
import xyz.zhangyi.ddd.eas.valueadded.trainingcontext.domain.ticket.Nominator;
import xyz.zhangyi.ddd.eas.valueadded.trainingcontext.domain.ticket.Nominee;
import xyz.zhangyi.ddd.eas.valueadded.trainingcontext.domain.ticket.Ticket;
import xyz.zhangyi.ddd.eas.valueadded.trainingcontext.domain.training.Training;
import xyz.zhangyi.ddd.eas.valueadded.trainingcontext.domain.training.TrainingException;
import xyz.zhangyi.ddd.eas.valueadded.trainingcontext.acl.port.repositories.TrainingRepository;
import xyz.zhangyi.ddd.eas.valueadded.trainingcontext.domain.validdate.ValidDate;
import xyz.zhangyi.ddd.eas.valueadded.trainingcontext.domain.validdate.ValidDateException;
import xyz.zhangyi.ddd.eas.valueadded.trainingcontext.acl.port.repositories.ValidDateRepository;
import xyz.zhangyi.ddd.eas.valueadded.trainingcontext.domain.validdate.ValidDateType;
import xyz.zhangyi.ddd.eas.valueadded.trainingcontext.acl.port.clients.NotificationClient;

import java.util.Optional;

@Service
public class NotificationService {
    @Autowired
    private MailTemplateRepository templateRepository;
    @Autowired
    private NotificationClient notificationClient;
    @Autowired
    private TrainingRepository trainingRepository;
    @Autowired
    private ValidDateRepository validDateRepository;

    public void notifyNominee(Ticket ticket, Nominator nominator, Nominee nominee) {
        MailTemplate mailTemplate = retrieveMailTemplate();
        Training training = retrieveTraining(ticket);
        ValidDate validDate = retrieveValidDate(ticket);

        VariableContext variableContext = buildVariableContext(ticket, nominator, nominee, training, validDate);
        Notification notification = mailTemplate.compose(variableContext);

        notificationClient.send(notification);
    }

    private MailTemplate retrieveMailTemplate() {
        Optional<MailTemplate> optionalMailTemplate = templateRepository.templateOf(TemplateType.Nomination);
        String mailTemplateNotFoundMessage = String.format("mail template by %s was not found.", TemplateType.Nomination);
        return optionalMailTemplate.orElseThrow(() -> new MailTemplateException(mailTemplateNotFoundMessage));
    }

    private Training retrieveTraining(Ticket ticket) {
        Optional<Training> optionalTraining = trainingRepository.trainingOf(ticket.trainingId());
        String trainingNotFoundMessage = String.format("training by id {%s} was not found.", ticket.trainingId());
        return optionalTraining.orElseThrow(() -> new TrainingException(trainingNotFoundMessage));
    }

    private ValidDate retrieveValidDate(Ticket ticket) {
        Optional<ValidDate> optionalValidDate = validDateRepository.validDateOf(ticket.trainingId(), ValidDateType.PODeadline);
        String validDateNotFoundMessage = String.format("valid date by training id {%s} was not found.", ticket.trainingId());
        return optionalValidDate.orElseThrow(() -> new ValidDateException(validDateNotFoundMessage));
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