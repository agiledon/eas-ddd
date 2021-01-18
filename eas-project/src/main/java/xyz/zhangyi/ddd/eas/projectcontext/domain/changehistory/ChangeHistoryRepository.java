package xyz.zhangyi.ddd.eas.projectcontext.domain.changehistory;

import xyz.zhangyi.ddd.core.stereotype.Port;
import xyz.zhangyi.ddd.core.stereotype.PortType;

@Port(PortType.Repository)
public interface ChangeHistoryRepository {
    void add(ChangeHistory changeHistory);
}