package xyz.zhangyi.ddd.eas.valueadded.trainingcontext.domain.notification;

public class TemplateVariable {
    private final String name;
    private final String value;

    private TemplateVariable(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public static TemplateVariable with(String name, String value) {
        return new TemplateVariable(name, value);
    }

    public String name() {
        return name;
    }

    public String value() {
        return value;
    }
}