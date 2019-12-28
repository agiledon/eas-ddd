package xyz.zhangyi.ddd.eas.projectcontext.domain.issue;

import xyz.zhangyi.ddd.eas.projectcontext.domain.changehistory.ChangeHistory;
import xyz.zhangyi.ddd.eas.projectcontext.domain.changehistory.Operation;
import xyz.zhangyi.ddd.eas.projectcontext.domain.changehistory.Operator;
import xyz.zhangyi.ddd.eas.projectcontext.domain.exceptions.AssignmentIssueException;
import java.time.LocalDateTime;

public class Issue {
    private IssueId issueId;
    private String name;
    private String description;
    private String ownerId;
    private IssueStatus status;

    private Issue(IssueId issueId, String name, String description) {
        this.issueId = issueId;
        this.name = name;
        this.description = description;
        this.status = IssueStatus.Open;
    }

    public static Issue of(IssueId issueId, String name, String description) {
        return new Issue(issueId, name, description);
    }

    public ChangeHistory assignTo(IssueOwner owner, Operator operator) {
        if (status.isResolved()) {
            throw new AssignmentIssueException("resolved issue can not be assigned.");
        }
        if (status.isClosed()) {
            throw new AssignmentIssueException("closed issue can not be assigned.");
        }
        if (this.ownerId != null && this.ownerId.equals(owner.id())) {
            throw new AssignmentIssueException("issue can not be assign to same owner again.");
        }
        this.ownerId = owner.id();
        return ChangeHistory
                .operate(Operation.Assignment)
                .to(issueId.id())
                .by(operator)
                .at(LocalDateTime.now());
    }

    public String ownerId() {
        return ownerId;
    }

    public IssueStatus status() {
        return status;
    }

    public void changeStatusTo(IssueStatus issueStatus) {
        this.status = issueStatus;
    }
}