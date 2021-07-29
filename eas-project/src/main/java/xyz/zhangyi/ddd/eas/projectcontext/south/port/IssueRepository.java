package xyz.zhangyi.ddd.eas.projectcontext.south.port;

import xyz.zhangyi.ddd.core.stereotype.Port;
import xyz.zhangyi.ddd.core.stereotype.PortType;
import xyz.zhangyi.ddd.eas.projectcontext.domain.issue.Issue;
import xyz.zhangyi.ddd.eas.projectcontext.domain.issue.IssueId;

import java.util.Optional;

@Port(PortType.Repository)
public interface IssueRepository {
    Optional<Issue> issueOf(IssueId issueId);
    void update(Issue issue);
}