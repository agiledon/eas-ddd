package xyz.zhangyi.ddd.eas.projectcontext.domain;

public class Issue {
    private IssueId issueId;
    private String name;
    private String description;
    private String ownerId;

    private Issue(IssueId issueId, String name, String description) {
        this.issueId = issueId;
        this.name = name;
        this.description = description;
    }

    public static Issue of(IssueId issueId, String name, String description) {
        return new Issue(issueId, name, description);
    }

    public void assignTo(String ownerId) {
        this.ownerId = ownerId;
    }

    public String ownerId() {
        return ownerId;
    }
}