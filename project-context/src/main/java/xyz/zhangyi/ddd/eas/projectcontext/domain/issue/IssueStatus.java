package xyz.zhangyi.ddd.eas.projectcontext.domain.issue;

public enum IssueStatus {
    Resolved, Open, Closed;

    public boolean isResolved() {
        return this == Resolved;
    }

    public boolean isClosed() {
        return this == Closed;
    }
}