package xyz.zhangyi.ddd.eas.projectcontext.domain;

import org.junit.Test;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class IssueServiceTest {
    @Test
    public void should_assign_issue_to_specific_owner_and_generate_change_history() {
        IssueId issueId = new IssueId("#1");
        Issue issue = Issue.of(issueId, "test issue", "test desc");
        String operatorId = "200010100007";
        IssueService issueService = new IssueService();

        IssueRepository issueRepo = mock(IssueRepository.class);
        when(issueRepo.issueOf(issueId)).thenReturn(Optional.of(issue));
        issueService.setIssueRepository(issueRepo);

        ChangeHistoryRepository changeHistoryRepo = mock(ChangeHistoryRepository.class);
        issueService.setChangeHistoryRepository(changeHistoryRepo);

        IssueOwner owner = new IssueOwner("200901010111", "zhangyi", "zhangyi@eas.com");
        issueService.assign(issueId, owner, operatorId);

        assertThat(issue.ownerId()).isEqualTo(owner.id());
        verify(issueRepo).issueOf(issueId);
        verify(issueRepo).update(issue);
        verify(changeHistoryRepo).add(isA(ChangeHistory.class));
    }
}