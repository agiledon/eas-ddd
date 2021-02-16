package xyz.zhangyi.ddd.eas.valueadded.trainingcontext.southbound.adapter.client;

import org.springframework.stereotype.Component;
import xyz.zhangyi.ddd.core.stereotype.Adapter;
import xyz.zhangyi.ddd.core.stereotype.PortType;
import xyz.zhangyi.ddd.eas.valueadded.trainingcontext.southbound.port.client.NotificationClient;
import xyz.zhangyi.ddd.eas.valueadded.trainingcontext.domain.notification.Notification;

@Adapter(PortType.Client)
@Component
public class NotificationClientAdapter implements NotificationClient {
    @Override
    public void send(Notification notification) {
        System.out.println("send the notification");
    }
}