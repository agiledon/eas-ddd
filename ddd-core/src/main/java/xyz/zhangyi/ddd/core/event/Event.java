package xyz.zhangyi.ddd.eas.core.event;

import java.io.Serializable;

public interface Event extends Serializable {
    String eventId();
}