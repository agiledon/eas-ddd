package xyz.zhangyi.ddd.eas.projectcontext.domain;

public interface ChangeHistoryRepository {
    void add(ChangeHistory changeHistory);
}