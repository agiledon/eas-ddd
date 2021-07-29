package xyz.zhangyi.ddd.eas.projectcontext.south.port;

import xyz.zhangyi.ddd.core.stereotype.Port;
import xyz.zhangyi.ddd.core.stereotype.PortType;
import xyz.zhangyi.ddd.eas.projectcontext.domain.changehistory.ChangeHistory;

@Port(PortType.Repository)
public interface ChangeHistoryRepository {
    void add(ChangeHistory changeHistory);
}