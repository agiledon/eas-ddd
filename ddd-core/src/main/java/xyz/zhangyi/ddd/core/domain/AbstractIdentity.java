package xyz.zhangyi.ddd.eas.core.domain;

import java.util.Objects;

public abstract class AbstractIdentity<T> implements Identity<T> {
    private T value;

    protected AbstractIdentity(T value) {
        this.setId(value);
    }

    @Override
    public T value() {
        return value;
    }

    private void setId(T value) {
        if (value == null) {
            throw new IllegalArgumentException("The identity is required ");
        }
        this.validateValue(value);
        this.value = value;
    }

    protected void validateValue(T value) {
        // validate value of Identity if need
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractIdentity<?> that = (AbstractIdentity<?>) o;
        return value.equals(that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + " [id=" + value + "]";
    }
}
