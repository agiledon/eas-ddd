package xyz.zhangyi.ddd.eas.trainingcontext.domain.notification;

import org.junit.Test;
import xyz.zhangyi.ddd.eas.trainingcontext.domain.ticket.*;
import xyz.zhangyi.ddd.eas.trainingcontext.domain.training.Training;
import xyz.zhangyi.ddd.eas.trainingcontext.domain.training.TrainingId;
import xyz.zhangyi.ddd.eas.trainingcontext.domain.validdate.ValidDate;
import xyz.zhangyi.ddd.eas.trainingcontext.domain.validdate.ValidDateType;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

public class MailTemplateTest {
    @Test
    public void should_compose_nomination_notification_given_ticket_and_nominator_and_nominee() {
        // given
        Nominator nominator = createNominator();
        Nominee nominee = createNominee();
        ValidDate validDate = createValidDate();
        Training training = createTraining();
        Ticket ticket = createTicket();

        String content = buildContent();

        MailTemplate mailTemplate = new MailTemplate(content, TemplateType.Nomination);

        // when
        Notification notification = mailTemplate.compose(training, ticket, validDate, nominator, nominee);

        // then
        assertThat(notification.from()).isEqualTo("admin@eas.com");
        assertThat(notification.to()).isEqualTo(nominee.email());
        assertThat(notification.subject()).isEqualTo("Ticket Nomination Notification");
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
        return new Ticket(TicketId.next(), "11111111");
    }

    private String buildContent() {
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

    private Training createTraining() {
        String trainingId = "111111111111";
        String courseId = "111111111111";
        LocalDateTime beginTime = LocalDateTime.of(2020, 1, 8, 9, 0);
        LocalDateTime endTime = LocalDateTime.of(2020, 1, 9, 17, 0);
        String place = "London Room";
        return new Training(TrainingId.from(trainingId), "ddd", "ddd training", beginTime, endTime, place, courseId);
    }

    private ValidDate createValidDate() {
        LocalDateTime poDeadline = LocalDateTime.of(2019, 12, 20, 0, 0);
        return new ValidDate(poDeadline, ValidDateType.PODeadline);
    }

    private Nominee createNominee() {
        return new Nominee("201001010011", "zhangyi", "zhangyi@eas.com");
    }

    private Nominator createNominator() {
        return new Nominator("200901010010", "bruce", "bruce@eas.com", TrainingRole.Coordinator);
    }
}