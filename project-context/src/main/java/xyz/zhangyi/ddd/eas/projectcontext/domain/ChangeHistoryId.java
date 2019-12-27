package xyz.zhangyi.ddd.eas.projectcontext.domain;

import java.util.UUID;

public class ChangeHistoryId {
    private final String id;

    private ChangeHistoryId(String id) {
        this.id = id;
    }

    public static ChangeHistoryId next() {
        return new ChangeHistoryId(UUID.randomUUID().toString());
    }
}