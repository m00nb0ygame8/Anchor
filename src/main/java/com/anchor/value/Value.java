package com.anchor.value;

import org.jetbrains.annotations.Nullable;

public class Value<T> extends AbstractValue<T> {
    private Class<T> type;

    public Value(@Nullable T initial) {
        super(initial);

        if(initial != null)
            //noinspection unchecked
            this.type = (Class<T>) initial.getClass();
    }

    @Override
    public void set(T val) {
        this.value = val;
        if(type == null)
            //noinspection unchecked
            this.type = (Class<T>) value.getClass();

    }

    @Override
    public T get() {
        return this.value;
    }

    @Override
    public Class<T> getType() {
        return type;
    }
}
