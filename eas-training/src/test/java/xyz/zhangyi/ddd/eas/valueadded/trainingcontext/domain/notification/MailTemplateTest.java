package xyz.zhangyi.ddd.eas.valueadded.trainingcontext.domain.notification;

import org.junit.Test;
import xyz.zhangyi.ddd.eas.valueadded.trainingcontext.domain.course.CourseId;
import xyz.zhangyi.ddd.eas.valueadded.trainingcontext.domain.training.Training;
import xyz.zhangyi.ddd.eas.valueadded.trainingcontext.domain.training.TrainingId;
import xyz.zhangyi.ddd.eas.valueadded.trainingcontext.domain.validate.ValidDate;
import xyz.zhangyi.ddd.eas.valueadded.trainingcontext.domain.validate.ValidDateType;
import xyz.zhangyi.ddd.eas.valueadded.trainingcontext.domain.ticket.*;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

public class MailTemplateTest {
    @Test
    public void should_compose_nomination_notification_given_ticket_and_nominator_and_nominee() {
        // given
        Nominator nominator = createNominator();
        Nominee nominee = createNominee();
        TrainingId trainingId = TrainingId.next();
        Training training = createTraining(trainingId);
        ValidDate validDate = createValidDate(trainingId);
        Ticket ticket = createTicket();

        String template = buildTemplate();
        VariableContext context = buildVariableContext(nominator, nominee, validDate, training, ticket);

        MailTemplate mailTemplate = new MailTemplate(template, TemplateType.Nomination);

        // when
        Notification notification = mailTemplate.compose(context);

        // then
        assertThat(notification.from()).isEqualTo("admin@eas.com");
        assertThat(notification.to()).isEqualTo(nominee.email());
        assertThat(notification.subject()).isEqualTo("Ticket Nomination Notification");
        assertNotificationBody(nominator, nominee, validDate, training, ticket, notification);
    }

    private VariableContext buildVariableContext(Nominator nominator, Nominee nominee, ValidDate validDate, Training training, Ticket ticket) {
        VariableContext context = new VariableContext();
        context.put("training", training);
        context.put("ticket", ticket);
        context.put("valid_date", validDate);
        context.put("nominator", nominator);
        context.put("nominee", nominee);
        return context;
    }

    private void assertNotificationBody(Nominator nominator, Nominee nominee, ValidDate validDate, Training training, Ticket ticket, Notification notification) {
        assertThat(notification.body()).containsIgnoringCase(String.format("Hi %s:", nominee.name()));
        assertThat(notification.body()).containsIgnoringCase(String.format("you are nominated by %s to attend the training", nominator.name()));
        assertThat(notification.body()).containsIgnoringCase(String.format("the deadline %s", validDate.deadline()));
        assertThat(notification.body()).containsIgnoringCase(ticket.url());
        assertThat(notification.body()).containsIgnoringCase(String.format("Title: %s", training.title()));
        assertThat(notification.body()).containsIgnoringCase(String.format("Description: %s", training.description()));
        assertThat(notification.body()).containsIgnoringCase(String.format("Begin time: %s", training.beginTime()));
        assertThat(notification.body()).containsIgnoringCase(String.format("End time: %s", training.endTime()));
        assertThat(notification.body()).containsIgnoringCase(String.format("Place: %s", training.place()));
    }

    private Ticket createTicket() {
        return new Ticket(TicketId.next(), TrainingId.next());
    }

    private String buildTemplate() {
        return "Hi $nomineeName$:\n" +
                "We are glad to notify that you are nominated by $nominatorName$ to attend the training. Please click the link as below to confirm this nomination before the deadline $deadline$:\n" +
                "$url$\n" +
                "\n" +
                "Here is the basic information of training:\n" +
                "Title: $title$\n" +
                "Description: $description$\n" +
                "Begin time: $beginTime$\n" +
                "End time: $endTime$\n" +
                "Place: $place$\n" +
                "\n" +
                "Thanks! We're excited to have you in the training.\n" +
                "EAS Team";
    }

    private Training createTraining(TrainingId trainingId) {
        CourseId courseId = CourseId.next();
        LocalDateTime beginTime = LocalDateTime.of(2020, 1, 8, 9, 0);
        LocalDateTime endTime = LocalDateTime.of(2020, 1, 9, 17, 0);
        String place = "London Room";
        return new Training(trainingId, "ddd", "ddd training", beginTime, endTime, place, courseId);
    }

    private ValidDate createValidDate(TrainingId trainingId) {
        LocalDateTime poDeadline = LocalDateTime.of(2019, 12, 20, 0, 0);
        return new ValidDate(trainingId, poDeadline, ValidDateType.PODeadline);
    }

    private Nominee createNominee() {
        return new Nominee("201001010011", "zhangyi", "zhangyi@eas.com");
    }

    private Nominator createNominator() {
        return new Nominator("200901010010", "bruce", "bruce@eas.com", TrainingRole.Coordinator);
    }
}