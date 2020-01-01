package xyz.zhangyi.ddd.eas.trainingcontext.domain.notification;

import org.stringtemplate.v4.ST;
import xyz.zhangyi.ddd.eas.trainingcontext.domain.ticket.Nominator;
import xyz.zhangyi.ddd.eas.trainingcontext.domain.ticket.Nominee;
import xyz.zhangyi.ddd.eas.trainingcontext.domain.ticket.Ticket;
import xyz.zhangyi.ddd.eas.trainingcontext.domain.training.Training;
import xyz.zhangyi.ddd.eas.trainingcontext.domain.validdate.ValidDate;

import java.util.ArrayList;
import java.util.List;

public class NotificationComposer {
    private static final char BEGIN_VARIABLE = '$';
    private static final char END_VARIABLE = '$';
    private String template;
    private List<TemplateVariable> variables;
    private final Training training;
    private final Ticket ticket;
    private final ValidDate validDate;
    private final Nominator nominator;
    private final Nominee nominee;

    public NotificationComposer(String template, VariableContext context) {
        variables = new ArrayList<>();
        this.template = template;
        training = context.get("training");
        ticket = context.get("ticket");
        validDate = context.get("valid_date");
        nominator = context.get("nominator");
        nominee = context.get("nominee");
    }

    public Notification compose() {
        String from = renderFrom();
        String to = renderTo();
        String subject = renderSubject();
        String body = renderBody();
        return new Notification(from, to, subject, body);
    }

    String renderFrom() {
        return "admin@eas.com";
    }

    String renderSubject() {
        return "Ticket Nomination Notification";
    }

    String renderTo() {
        return nominee.email();
    }

    String renderBody() {
        registerVariables();

        ST st = new ST(template, BEGIN_VARIABLE, END_VARIABLE);
        for (TemplateVariable variable : variables) {
            st.add(variable.name(), variable.value());
        }
        return st.render();
    }

    private void registerVariables() {
        variables.add(TemplateVariable.with("nomineeName", nominee.name()));
        variables.add(TemplateVariable.with("nominatorName", nominator.name()));
        variables.add(TemplateVariable.with("url", ticket.url()));
        variables.add(TemplateVariable.with("title", training.title()));
        variables.add(TemplateVariable.with("description", training.description()));
        variables.add(TemplateVariable.with("beginTime", training.beginTime().toString()));
        variables.add(TemplateVariable.with("endTime", training.endTime().toString()));
        variables.add(TemplateVariable.with("place", training.place()));
        variables.add(TemplateVariable.with("deadline", validDate.deadline().toString()));
    }
}