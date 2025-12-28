package com.anchor;

import com.anchor.batched.Transaction;
import com.anchor.composed.ConstraintExtension;
import org.composed.ComposedExtension;

public class Anchor {
    public static final ComposedExtension<ConstraintExtension> CONSTRAINT_EXTENSION = new ComposedExtension<>(ConstraintExtension.class);

    public static void transaction(Transaction transaction) {
        Transaction.run(transaction);
    }

}
