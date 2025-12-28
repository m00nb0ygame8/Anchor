package com.anchor.composed;

import com.anchor.Constraint;
import com.anchor.value.AbstractValue;

import java.util.List;

public record TypedConstraintExtension<T>(List<Constraint<T>> constraints) {

    public void onValueSet(AbstractValue<T> post) {
        constraints.forEach(constraint -> {
            switch (constraint.getConstraintType()) {
                case ALWAYS, ON_SET: {
                    constraint.getConstrainer().accept(post);
                }
            }
        });
    }

    public void onValueGet(AbstractValue<T> pre) {
        constraints.forEach(constraint -> {
            switch (constraint.getConstraintType()) {
                case ALWAYS, ON_GET: {
                    constraint.getConstrainer().accept(pre);
                }
            }
        });
    }
}
