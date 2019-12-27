package xyz.zhangyi.ddd.eas.projectcontext.domain;

import org.junit.Before;
import org.junit.Test;
import java.time.LocalDateTime;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import xyz.zhangyi.ddd.eas.projectcontext.domain.exceptions.AssignmentIssueException;

public class IssueTest {
    private IssueId issueId;
    private String name;
    private String description;
    private String ownerId;
    private String operatorId;

    @Before
    public void setUp() {
        issueId = new IssueId("#1");
        name = "test issue";
        description = "description";
        ownerId = "200901010110";
        operatorId = "200001010007";
    }

    @Test
    public void should_be_OPEN_when_new_issue_is_created() {
        Issue issue = Issue.of(issueId, name, description);

        assertThat(issue.status()).isEqualTo(IssueStatus.Open);
    }

    @Test
    public void should_be_changed_to_target_status() {
        Issue issue = Issue.of(issueId, name, description);

        assertThat(issue.status()).isEqualTo(IssueStatus.Open);

        issue.changeStatusTo(IssueStatus.Resolved);

        assertThat(issue.status()).isEqualTo(IssueStatus.Resolved);
    }

    @Test
    public void should_assign_to_specific_owner_and_generate_change_history() {
        Issue issue = Issue.of(issueId, name, description);

        ChangeHistory history = issue.assignTo(ownerId, operatorId);

        assertThat(issue.ownerId()).isEqualTo(ownerId);
        assertThat(history.issueId()).isEqualTo(issueId.id());
        assertThat(history.operatedBy()).isEqualTo(operatorId);
        assertThat(history.operation()).isEqualTo(Operation.Assignment);
        assertThat(history.operatedAt()).isEqualToIgnoringSeconds(LocalDateTime.now());
    }

    @Test
    public void should_throw_AssignmentIssueException_when_assign_resolved_issue() {
        Issue issue = Issue.of(issueId, name, description);
        issue.changeStatusTo(IssueStatus.Resolved);

        assertThatThrownBy(() -> issue.assignTo(ownerId, operatorId))
                .isInstanceOf(AssignmentIssueException.class)
                .hasMessageContaining("resolved issue can not be assigned");
    }

    @Test
    public void should_throw_AssignmentIssueException_when_assign_closed_issue() {
        Issue issue = Issue.of(issueId, name, description);
        issue.changeStatusTo(IssueStatus.Closed);

        assertThatThrownBy(() -> issue.assignTo(ownerId, operatorId))
                .isInstanceOf(AssignmentIssueException.class)
                .hasMessageContaining("closed issue can not be assigned");
    }

    @Test
    public void should_throw_AssignmentIssueException_when_issue_is_assigned_to_same_owner() {
        Issue issue = Issue.of(issueId, name, description);
        issue.assignTo(ownerId, operatorId);

        assertThatThrownBy(() -> issue.assignTo(ownerId, operatorId))
                .isInstanceOf(AssignmentIssueException.class)
                .hasMessageContaining("issue can not be assign to same owner again");
    }
}