package xyz.zhangyi.ddd.eas.trainingcontext.gateway.impl.client;

import org.springframework.stereotype.Component;
import xyz.zhangyi.ddd.eas.trainingcontext.domain.notification.Notification;
import xyz.zhangyi.ddd.eas.trainingcontext.gateway.interfaces.NotificationClient;

@Component
public class NotificationClientImpl implements NotificationClient {
    @Override
    public void send(Notification notification) {
        System.out.println("send the notification");
    }
}