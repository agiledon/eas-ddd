package xyz.zhangyi.ddd.eas.core.domain;

import java.io.Serializable;

public interface Event extends Serializable {
    String eventId();
}