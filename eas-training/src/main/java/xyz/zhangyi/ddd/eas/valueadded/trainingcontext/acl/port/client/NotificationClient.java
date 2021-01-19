package xyz.zhangyi.ddd.eas.valueadded.trainingcontext.acl.port.client;

import xyz.zhangyi.ddd.core.stereotype.Port;
import xyz.zhangyi.ddd.core.stereotype.PortType;
import xyz.zhangyi.ddd.eas.valueadded.trainingcontext.domain.notification.Notification;

@Port(PortType.Client)
public interface NotificationClient {
    void send(Notification notification);
}
