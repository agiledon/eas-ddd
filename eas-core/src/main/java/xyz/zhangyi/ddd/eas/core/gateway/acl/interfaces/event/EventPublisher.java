package xyz.zhangyi.ddd.eas.core.gateway.acl.interfaces.event;

import xyz.zhangyi.ddd.eas.core.domain.Event;

public interface EventPublisher<T extends Event> {
    void publish(T event);
}