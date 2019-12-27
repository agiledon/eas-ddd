package xyz.zhangyi.ddd.eas.projectcontext.domain;

import java.time.LocalDateTime;

public class ChangeHistory {
    private ChangeHistoryId changeHistoryId;
    private Operation operation;
    private String issueId;
    private String operatedBy;
    private LocalDateTime operatedAt;

    public ChangeHistory(Operation operation) {
        this.changeHistoryId = ChangeHistoryId.next();
        this.operation = operation;
    }

    public static ChangeHistory operate(Operation operation) {
        return new ChangeHistory(operation);
    }

    public String issueId() {
        return this.issueId;
    }

    public String operatedBy() {
        return this.operatedBy;
    }

    public Operation operation() {
        return this.operation;
    }

    public LocalDateTime operatedAt() {
        return this.operatedAt;
    }

    public ChangeHistory to(String issueId) {
        this.issueId = issueId;
        return this;
    }

    public ChangeHistory by(String operatorId) {
        this.operatedBy = operatorId;
        return this;
    }

    public ChangeHistory at(LocalDateTime operatedTime) {
        this.operatedAt = operatedTime;
        return this;
    }
}