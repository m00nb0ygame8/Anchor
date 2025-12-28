package com.anchor.value;

import org.jetbrains.annotations.NotNull;

public class ImmutableValue<T> extends AbstractValue<T> {
    private final Class<T> type;

    public ImmutableValue(@NotNull T val) {
        super(val);
        //noinspection unchecked
        this.type = (Class<T>) val.getClass();
    }

    @Override
    public void set(T val) {
        throw new IllegalStateException("Cannot change immutable value!");
    }

    @Override
    public T get() {
        return this.value;
    }

    @Override
    public Class<T> getType() {
        return this.type;
    }

    public static <Q> ImmutableValue<Q> of(AbstractValue<Q> other) {
        return new ImmutableValue<>(other.get());
    }
}
