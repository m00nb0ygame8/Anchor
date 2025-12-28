package com.anchor;

import com.anchor.value.AbstractValue;
import com.anchor.value.ImmutableValue;

import java.util.function.Supplier;

@SuppressWarnings("ClassCanBeRecord")
public class Derived<T> {
    private final AbstractValue<T> value;

    public Derived(Supplier<T> factory) {
        this.value = new DerivedValue<>(factory);
    }

    public ImmutableValue<T> snapshot() {
        return ImmutableValue.of(value);
    }

    public AbstractValue<T> held() {
        return this.value;
    }

    public static <T> AbstractValue<T> of(Supplier<T> factory) {
        return new Derived<>(factory).held();
    }

    public static class DerivedValue<T> extends AbstractValue<T> {
        private final Supplier<T> valFactory;

        public DerivedValue(Supplier<T> valFactory) {
            super(null);
            this.valFactory = valFactory;
        }

        @Override
        public void set(T val) {
            throw new IllegalStateException("Cannot set the value of a Derived Value!");
        }

        @Override
        public T get() {
            return this.valFactory.get();
        }

        @Override
        public Class<T> getType() {
            return null;
        }
    }
}
