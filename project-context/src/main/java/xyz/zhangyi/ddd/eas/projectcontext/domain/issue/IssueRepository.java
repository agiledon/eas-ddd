package xyz.zhangyi.ddd.eas.projectcontext.domain.issue;

import java.util.Optional;

public interface IssueRepository {
    Optional<Issue> issueOf(IssueId issueId);
    void update(Issue issue);
}