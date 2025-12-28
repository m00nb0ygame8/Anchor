package com.anchor.value;

import org.jetbrains.annotations.Nullable;

public abstract class AbstractValue<T> {
    protected T value;

    public AbstractValue(@Nullable T initial) {
        this.value = initial;
    }

    public abstract void set(T val);

    public abstract T get();

    public abstract Class<T> getType();
}
