package xyz.zhangyi.ddd.eas.trainingcontext.gateway.acl.interfaces;

import xyz.zhangyi.ddd.eas.trainingcontext.domain.notification.Notification;

public interface NotificationClient {
    void send(Notification notification);
}
