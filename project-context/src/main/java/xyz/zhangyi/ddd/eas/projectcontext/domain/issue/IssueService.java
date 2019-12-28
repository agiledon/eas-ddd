package xyz.zhangyi.ddd.eas.projectcontext.domain.issue;

import xyz.zhangyi.ddd.eas.projectcontext.domain.changehistory.ChangeHistory;
import xyz.zhangyi.ddd.eas.projectcontext.domain.changehistory.ChangeHistoryRepository;
import xyz.zhangyi.ddd.eas.projectcontext.domain.changehistory.Operator;
import xyz.zhangyi.ddd.eas.projectcontext.domain.exceptions.IssueException;

import java.util.Optional;

public class IssueService {
    private IssueRepository issueRepo;
    private ChangeHistoryRepository changeHistoryRepo;

    public void assign(IssueId issueId, IssueOwner owner, Operator operator) {
        Optional<Issue> optIssue = issueRepo.issueOf(issueId);
        Issue issue = optIssue.orElseThrow(
                () -> new IssueException(String.format("issue with id {%s} was not found", issueId.id())));

        ChangeHistory changeHistory = issue.assignTo(owner, operator);

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