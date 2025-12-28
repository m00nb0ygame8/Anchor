package com.anchor.batched;

import com.anchor.composed.TypedConstraintExtension;
import com.anchor.value.AbstractValue;
import com.anchor.value.Value;
import org.composed.Composition;

import static com.anchor.Anchor.CONSTRAINT_EXTENSION;

public class TransactionInternal {

    TransactionInternal() {}

    public  <T> T get(AbstractValue<T> value) {
        if(Composition.hasComposition(value) && CONSTRAINT_EXTENSION.has(value)) {
            //noinspection unchecked
            TypedConstraintExtension<T> extension = (TypedConstraintExtension<T>) CONSTRAINT_EXTENSION.get(value)
                    .getExtension();

            extension.onValueGet(value);
        }
        return value.get();
    }

    public  <T> void set(AbstractValue<T> value, T newVal) {
        if(Composition.hasComposition(value) && CONSTRAINT_EXTENSION.has(value)) {
            //noinspection unchecked
            TypedConstraintExtension<T> extension = (TypedConstraintExtension<T>) CONSTRAINT_EXTENSION.get(value)
                    .getExtension();

            Value<T> temp = new Value<>(newVal);
            extension.onValueSet(temp);
            value.set(temp.get());
        } else value.set(newVal);
    }
}
