package xyz.zhangyi.ddd.eas.trainingcontext.domain.notification;

import org.junit.Test;
import xyz.zhangyi.ddd.eas.trainingcontext.domain.course.CourseId;
import xyz.zhangyi.ddd.eas.trainingcontext.domain.ticket.*;
import xyz.zhangyi.ddd.eas.trainingcontext.domain.training.Training;
import xyz.zhangyi.ddd.eas.trainingcontext.domain.training.TrainingException;
import xyz.zhangyi.ddd.eas.trainingcontext.domain.training.TrainingId;
import xyz.zhangyi.ddd.eas.trainingcontext.domain.training.TrainingRepository;
import xyz.zhangyi.ddd.eas.trainingcontext.domain.validdate.ValidDate;
import xyz.zhangyi.ddd.eas.trainingcontext.domain.validdate.ValidDateException;
import xyz.zhangyi.ddd.eas.trainingcontext.domain.validdate.ValidDateRepository;
import xyz.zhangyi.ddd.eas.trainingcontext.domain.validdate.ValidDateType;
import xyz.zhangyi.ddd.eas.trainingcontext.gateway.interfaces.NotificationClient;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;
import static xyz.zhangyi.ddd.eas.trainingcontext.domain.ticket.TicketStatus.Available;

public class NotificationServiceTest {
    @Test
    public void should_compose_notification_then_send_it_via_notification_client() {
        // given
        TrainingId trainingId = TrainingId.next();

        Ticket ticket = new Ticket(TicketId.next(), trainingId, Available);
        Nominator nominator = new Nominator("200901010007", "admin", "admin@eas.com", TrainingRole.Coordinator);
        Nominee nominee = new Nominee("200910100111", "bruce", "bruce@email.com");

        CourseId courseId = CourseId.next();
        LocalDateTime beginTime = LocalDateTime.of(2020, 1, 8, 9, 0);
        LocalDateTime endTime = LocalDateTime.of(2020, 1, 9, 17, 0);
        String place = "London Room";
        Training training = new Training(trainingId, "DDD", "DDD Course", beginTime, endTime, place, courseId);

        MailTemplate mailTemplate = new MailTemplate(buildTemplate(), TemplateType.Nomination);
        MailTemplateRepository mockTemplateRepo = mock(MailTemplateRepository.class);
        when(mockTemplateRepo.templateOf(TemplateType.Nomination)).thenReturn(Optional.of(mailTemplate));

        NotificationClient mockNotificationClient = mock(NotificationClient.class);

        TrainingRepository mockTrainingRepo = mock(TrainingRepository.class);
        when(mockTrainingRepo.trainingOf(trainingId)).thenReturn(Optional.of(training));

        LocalDateTime poDeadline = LocalDateTime.of(2019, 12, 20, 0, 0);
        ValidDate validDate = new ValidDate(poDeadline, ValidDateType.PODeadline);
        ValidDateRepository mockValidDateRepo = mock(ValidDateRepository.class);
        when(mockValidDateRepo.validDateOf(trainingId, ValidDateType.PODeadline)).thenReturn(Optional.of(validDate));

        NotificationService notificationService = createNotificationService(
                mockTemplateRepo,
                mockNotificationClient,
                mockTrainingRepo,
                mockValidDateRepo);
        //when
        notificationService.notifyNominee(ticket, nominator, nominee);

        // then
        verify(mockTemplateRepo).templateOf(TemplateType.Nomination);
        verify(mockTrainingRepo).trainingOf(trainingId);
        verify(mockValidDateRepo).validDateOf(trainingId, ValidDateType.PODeadline);
        verify(mockNotificationClient).send(isA(Notification.class));
    }

    @Test
    public void should_throw_MailTemplateException_if_mail_template_was_not_found() {
        // given
        TrainingId trainingId = TrainingId.next();

        Ticket ticket = new Ticket(TicketId.next(), trainingId, Available);
        Nominator nominator = new Nominator("200901010007", "admin", "admin@eas.com", TrainingRole.Coordinator);
        Nominee nominee = new Nominee("200910100111", "bruce", "bruce@email.com");

        MailTemplateRepository mockTemplateRepo = mock(MailTemplateRepository.class);
        when(mockTemplateRepo.templateOf(TemplateType.Nomination)).thenReturn(Optional.empty());

        NotificationClient mockNotificationClient = mock(NotificationClient.class);
        TrainingRepository mockTrainingRepo = mock(TrainingRepository.class);
        ValidDateRepository mockValidDateRepo = mock(ValidDateRepository.class);

        NotificationService notificationService = createNotificationService(
                mockTemplateRepo,
                mockNotificationClient,
                mockTrainingRepo,
                mockValidDateRepo);
        // then
        assertThatThrownBy(() -> notificationService.notifyNominee(ticket, nominator, nominee))
                .isInstanceOf(MailTemplateException.class)
                .hasMessageContaining(String.format("mail template by %s was not found", TemplateType.Nomination));

        verify(mockTemplateRepo).templateOf(TemplateType.Nomination);
        verify(mockTrainingRepo, never()).trainingOf(trainingId);
        verify(mockValidDateRepo, never()).validDateOf(trainingId, ValidDateType.PODeadline);
        verify(mockNotificationClient, never()).send(isA(Notification.class));
    }

    @Test
    public void should_throw_TrainingException_if_training_was_not_found() {
        // given
        TrainingId trainingId = TrainingId.next();

        Ticket ticket = new Ticket(TicketId.next(), trainingId, Available);
        Nominator nominator = new Nominator("200901010007", "admin", "admin@eas.com", TrainingRole.Coordinator);
        Nominee nominee = new Nominee("200910100111", "bruce", "bruce@email.com");

        CourseId courseId = CourseId.next();
        LocalDateTime beginTime = LocalDateTime.of(2020, 1, 8, 9, 0);
        LocalDateTime endTime = LocalDateTime.of(2020, 1, 9, 17, 0);
        String place = "London Room";
        Training training = new Training(trainingId, "DDD", "DDD Course", beginTime, endTime, place, courseId);

        MailTemplate mailTemplate = new MailTemplate(buildTemplate(), TemplateType.Nomination);
        MailTemplateRepository mockTemplateRepo = mock(MailTemplateRepository.class);
        when(mockTemplateRepo.templateOf(TemplateType.Nomination)).thenReturn(Optional.of(mailTemplate));

        NotificationClient mockNotificationClient = mock(NotificationClient.class);

        TrainingRepository mockTrainingRepo = mock(TrainingRepository.class);
        when(mockTrainingRepo.trainingOf(trainingId)).thenReturn(Optional.empty());

        ValidDateRepository mockValidDateRepo = mock(ValidDateRepository.class);

        NotificationService notificationService = createNotificationService(
                mockTemplateRepo,
                mockNotificationClient,
                mockTrainingRepo,
                mockValidDateRepo);

        // then
        assertThatThrownBy(() -> notificationService.notifyNominee(ticket, nominator, nominee))
                .isInstanceOf(TrainingException.class)
                .hasMessageContaining(String.format("training by id {%s} was not found", trainingId));

        verify(mockTemplateRepo).templateOf(TemplateType.Nomination);
        verify(mockTrainingRepo).trainingOf(trainingId);
        verify(mockValidDateRepo, never()).validDateOf(trainingId, ValidDateType.PODeadline);
        verify(mockNotificationClient, never()).send(isA(Notification.class));
    }

    @Test
    public void should_throw_ValidDateException_if_valid_date_was_not_found() {
        // given
        TrainingId trainingId = TrainingId.next();

        Ticket ticket = new Ticket(TicketId.next(), trainingId, Available);
        Nominator nominator = new Nominator("200901010007", "admin", "admin@eas.com", TrainingRole.Coordinator);
        Nominee nominee = new Nominee("200910100111", "bruce", "bruce@email.com");

        CourseId courseId = CourseId.next();
        LocalDateTime beginTime = LocalDateTime.of(2020, 1, 8, 9, 0);
        LocalDateTime endTime = LocalDateTime.of(2020, 1, 9, 17, 0);
        String place = "London Room";
        Training training = new Training(trainingId, "DDD", "DDD Course", beginTime, endTime, place, courseId);

        MailTemplate mailTemplate = new MailTemplate(buildTemplate(), TemplateType.Nomination);
        MailTemplateRepository mockTemplateRepo = mock(MailTemplateRepository.class);
        when(mockTemplateRepo.templateOf(TemplateType.Nomination)).thenReturn(Optional.of(mailTemplate));

        NotificationClient mockNotificationClient = mock(NotificationClient.class);

        TrainingRepository mockTrainingRepo = mock(TrainingRepository.class);
        when(mockTrainingRepo.trainingOf(trainingId)).thenReturn(Optional.of(training));

        ValidDateRepository mockValidDateRepo = mock(ValidDateRepository.class);
        when(mockValidDateRepo.validDateOf(trainingId, ValidDateType.PODeadline)).thenReturn(Optional.empty());

        NotificationService notificationService = createNotificationService(
                mockTemplateRepo,
                mockNotificationClient,
                mockTrainingRepo,
                mockValidDateRepo);

        // then
        assertThatThrownBy(() -> notificationService.notifyNominee(ticket, nominator, nominee))
                .isInstanceOf(ValidDateException.class)
                .hasMessageContaining(String.format("valid date by training id {%s} was not found", trainingId));

        verify(mockTemplateRepo).templateOf(TemplateType.Nomination);
        verify(mockTrainingRepo).trainingOf(trainingId);
        verify(mockValidDateRepo).validDateOf(trainingId, ValidDateType.PODeadline);
        verify(mockNotificationClient, never()).send(isA(Notification.class));
    }

    private NotificationService createNotificationService(MailTemplateRepository mockTemplateRepo, NotificationClient mockNotificationClient, TrainingRepository mockTrainingRepo, ValidDateRepository mockValidDateRepo) {
        NotificationService notificationService = new NotificationService();
        notificationService.setMailTemplateRepository(mockTemplateRepo);
        notificationService.setNotificationClient(mockNotificationClient);
        notificationService.setTrainingRepository(mockTrainingRepo);
        notificationService.setValidDateRepository(mockValidDateRepo);
        return notificationService;
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
}