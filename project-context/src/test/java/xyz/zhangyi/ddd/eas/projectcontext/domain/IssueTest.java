package xyz.zhangyi.ddd.eas.projectcontext.domain;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class IssueTest {
    @Test
    public void should_assign_to_specific_owner() {
        IssueId issueId = IssueId.next();
        String name = "test issue";
        String description = "description";
        Issue issue = Issue.of(issueId, name, description);
        String ownerId = "200901010110";

        issue.assignTo(ownerId);

        assertThat(issue.ownerId()).isEqualTo(ownerId);
    }
}