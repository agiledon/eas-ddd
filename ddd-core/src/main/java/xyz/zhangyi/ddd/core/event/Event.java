package xyz.zhangyi.ddd.core.event;

import java.io.Serializable;

public interface Event extends Serializable {
    String eventId();
}