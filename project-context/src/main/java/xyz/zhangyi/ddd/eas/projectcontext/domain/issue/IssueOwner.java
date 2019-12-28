package xyz.zhangyi.ddd.eas.projectcontext.domain.issue;

public class IssueOwner {
    private String ownerId;
    private String name;
    private String email;

    public IssueOwner(String ownerId, String name, String email) {
        this.ownerId = ownerId;
        this.name = name;
        this.email = email;
    }

    public String id() {
        return this.ownerId;
    }
}