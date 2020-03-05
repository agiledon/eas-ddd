package xyz.zhangyi.ddd.eas.trainingcontext.acl.ports.clients;

import xyz.zhangyi.ddd.eas.trainingcontext.domain.notification.Notification;

public interface NotificationClient {
    void send(Notification notification);
}
