package xyz.zhangyi.ddd.eas.projectcontext.domain.changehistory;

public interface ChangeHistoryRepository {
    void add(ChangeHistory changeHistory);
}