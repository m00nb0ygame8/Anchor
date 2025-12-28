package com.anchor;

import com.anchor.composed.ConstraintExtension;
import com.anchor.composed.TypedConstraintExtension;
import com.anchor.value.AbstractValue;
import org.composed.Composition;

import java.util.function.Consumer;

public class Constraint<T> {
    private final Type constraintType;
    private final Consumer<AbstractValue<T>> constrainer;

    private Constraint(Type type, Consumer<AbstractValue<T>> constrainer, AbstractValue<T> value) {
        if(value instanceof Derived.DerivedValue<T>) throw new RuntimeException("Cannot constrain a derived value!");
        this.constraintType = type;
        this.constrainer = constrainer;
        Composition valWrapped = Composition.of(value);
        if(!valWrapped.has(ConstraintExtension.class)) {
            if(value.getType() == null) throw new IllegalStateException("Cannot constrain a null value with a null value type!");
            valWrapped.extend(ConstraintExtension.class, new ConstraintExtension(value.getType()));
        }
        ConstraintExtension constraintExtension = valWrapped.get(ConstraintExtension.class);
        //noinspection unchecked
        TypedConstraintExtension<T> extension = (TypedConstraintExtension<T>) constraintExtension.getExtension();
        extension.constraints().add(this);
    }

    public Type getConstraintType() {
        return constraintType;
    }

    public Consumer<AbstractValue<T>> getConstrainer() {
        return constrainer;
    }

    public static <T> void always(Consumer<AbstractValue<T>> consumer, AbstractValue<T> value) {
        new Constraint<>(Type.ALWAYS, consumer, value);
    }
    public static <T> void onGet(Consumer<AbstractValue<T>> consumer, AbstractValue<T> value) {
        new Constraint<>(Type.ON_GET, consumer, value);
    }
    public static <T> void onSet(Consumer<AbstractValue<T>> consumer, AbstractValue<T> value) {
        new Constraint<>(Type.ON_SET, consumer, value);
    }

    public enum Type {
        ALWAYS,
        ON_GET,
        ON_SET
    }
}
