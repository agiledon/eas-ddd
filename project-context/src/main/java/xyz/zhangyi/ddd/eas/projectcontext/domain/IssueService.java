package xyz.zhangyi.ddd.eas.projectcontext.domain;

import java.util.Optional;

public class IssueService {
    private IssueRepository issueRepo;
    private ChangeHistoryRepository changeHistoryRepo;

    public void assign(IssueId issueId, IssueOwner owner, String operatorId) {
        Optional<Issue> optIssue = issueRepo.issueOf(issueId);
        Issue issue = optIssue.get();

        ChangeHistory changeHistory = issue.assignTo(owner.id(), operatorId);

        issueRepo.update(issue);
        changeHistoryRepo.add(changeHistory);
    }

    public void setIssueRepository(IssueRepository issueRepo) {
        this.issueRepo = issueRepo;
    }

    public void setChangeHistoryRepository(ChangeHistoryRepository changeHistoryRepo) {
        this.changeHistoryRepo = changeHistoryRepo;
    }
}