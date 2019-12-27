package xyz.zhangyi.ddd.eas.projectcontext.domain;

import org.junit.Before;
import org.junit.Test;
import xyz.zhangyi.ddd.eas.projectcontext.domain.exceptions.AssignmentIssueException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class IssueTest {

    private IssueId issueId;
    private String name;
    private String description;
    private String ownerId;

    @Before
    public void setUp() {
        issueId = IssueId.next();
        name = "test issue";
        description = "description";
        ownerId = "200901010110";
    }

    @Test
    public void should_assign_to_specific_owner() {
        Issue issue = Issue.of(issueId, name, description);

        issue.assignTo(ownerId);

        assertThat(issue.ownerId()).isEqualTo(ownerId);
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
    public void should_throw_AssignmentIssueException_when_assign_resolved_issue() {
        Issue issue = Issue.of(issueId, name, description);
        issue.changeStatusTo(IssueStatus.Resolved);

        assertThatThrownBy(() -> issue.assignTo(ownerId))
                .isInstanceOf(AssignmentIssueException.class)
                .hasMessageContaining("resolved issue can not be assigned");
    }

    @Test
    public void should_throw_AssignmentIssueException_when_assign_closed_issue() {
        Issue issue = Issue.of(issueId, name, description);
        issue.changeStatusTo(IssueStatus.Closed);

        assertThatThrownBy(() -> issue.assignTo(ownerId))
                .isInstanceOf(AssignmentIssueException.class)
                .hasMessageContaining("closed issue can not be assigned");
    }

    @Test
    public void should_throw_AssignmentIssueException_when_issue_is_assigned_to_same_owner() {
        Issue issue = Issue.of(issueId, name, description);
        issue.assignTo(ownerId);

        assertThatThrownBy(() -> issue.assignTo(ownerId))
                .isInstanceOf(AssignmentIssueException.class)
                .hasMessageContaining("issue can not be assign to same owner again");
    }

    @Test
    public void should_throw_AssignmentIssueException_when_passed_ownerId_is_null() {
        Issue issue = Issue.of(issueId, name, description);

        assertThatThrownBy(() -> issue.assignTo(null))
                .isInstanceOf(AssignmentIssueException.class)
                .hasMessageContaining("owner id is null");
    }

    @Test
    public void should_throw_AssignmentIssueException_when_passed_ownerId_is_empty() {
        Issue issue = Issue.of(issueId, name, description);

        assertThatThrownBy(() -> issue.assignTo(""))
                .isInstanceOf(AssignmentIssueException.class)
                .hasMessageContaining("owner id is null");
    }
}