package xyz.zhangyi.ddd.eas.core.gateway.acl.port;

import xyz.zhangyi.ddd.eas.core.event.Event;

public interface EventPublisher<T extends Event> {
    void publish(T event);
}