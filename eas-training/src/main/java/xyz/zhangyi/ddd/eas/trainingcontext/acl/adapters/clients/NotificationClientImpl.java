package xyz.zhangyi.ddd.eas.trainingcontext.acl.adapters.clients;

import org.springframework.stereotype.Component;
import xyz.zhangyi.ddd.eas.trainingcontext.acl.ports.clients.NotificationClient;
import xyz.zhangyi.ddd.eas.trainingcontext.domain.notification.Notification;

@Component
public class NotificationClientImpl implements NotificationClient {
    @Override
    public void send(Notification notification) {
        System.out.println("send the notification");
    }
}