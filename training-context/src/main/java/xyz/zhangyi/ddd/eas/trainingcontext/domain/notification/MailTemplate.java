package xyz.zhangyi.ddd.eas.trainingcontext.domain.notification;

import org.stringtemplate.v4.ST;
import xyz.zhangyi.ddd.eas.trainingcontext.domain.ticket.Nominator;
import xyz.zhangyi.ddd.eas.trainingcontext.domain.ticket.Nominee;
import xyz.zhangyi.ddd.eas.trainingcontext.domain.ticket.Ticket;
import xyz.zhangyi.ddd.eas.trainingcontext.domain.training.Training;
import xyz.zhangyi.ddd.eas.trainingcontext.domain.validdate.ValidDate;

import java.util.ArrayList;
import java.util.List;

public class MailTemplate {
    private static final char BEGIN_VARIABLE = '$';
    private static final char END_VARIABLE = '$';
    private String content;
    private List<TemplateVariable> variables;

    public MailTemplate(String content, TemplateType templateType) {
        this.variables = new ArrayList<>();
        this.content = content;
    }

    public Notification compose(Training training, Ticket ticket, ValidDate validDate, Nominator nominator, Nominee nominee) {
        variables.add(TemplateVariable.with("nomineeName", nominee.name()));
        variables.add(TemplateVariable.with("nominatorName", nominator.name()));
        variables.add(TemplateVariable.with("url", ticket.url()));
        variables.add(TemplateVariable.with("title", training.title()));
        variables.add(TemplateVariable.with("description", training.description()));
        variables.add(TemplateVariable.with("beginTime", training.beginTime().toString()));
        variables.add(TemplateVariable.with("endTime", training.endTime().toString()));
        variables.add(TemplateVariable.with("place", training.place()));
        variables.add(TemplateVariable.with("deadline", validDate.deadline().toString()));

        ST st = new ST(content, BEGIN_VARIABLE, END_VARIABLE);
        for (TemplateVariable variable : variables) {
            st.add(variable.name(), variable.value());
        }
        String body = st.render();
        String from = "admin@eas.com";
        String to = nominee.email();
        String subject = "Ticket Nomination Notification";
        return new Notification(from, to, subject, body);
    }
}