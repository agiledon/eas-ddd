package xyz.zhangyi.ddd.core.gateway.acl.port;

import xyz.zhangyi.ddd.core.event.Event;

public interface EventPublisher<T extends Event> {
    void publish(T event);
}