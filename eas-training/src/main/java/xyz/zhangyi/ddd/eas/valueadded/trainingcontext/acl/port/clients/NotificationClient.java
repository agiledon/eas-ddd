package xyz.zhangyi.ddd.eas.valueadded.trainingcontext.acl.port.clients;

import xyz.zhangyi.ddd.eas.valueadded.trainingcontext.domain.notification.Notification;

public interface NotificationClient {
    void send(Notification notification);
}
