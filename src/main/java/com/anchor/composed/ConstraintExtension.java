package com.anchor.composed;

import org.composed.Extension;

import java.util.ArrayList;

public class ConstraintExtension implements Extension {
    private final ConstraintHolder<?> holder;

    public <T> ConstraintExtension(Class<T> type) {
        TypedConstraintExtension<T> extension = new TypedConstraintExtension<>(new ArrayList<>());
        this.holder = new ConstraintHolder<>(type, extension);
    }

    public ConstraintHolder<?> getHolder() {
        return holder;
    }
    public TypedConstraintExtension<?> getExtension() {
        return getHolder().extension();
    }

    public record ConstraintHolder<T>(Class<T> type, TypedConstraintExtension<T> extension) {}
}
