package com.anchor.batched;

@FunctionalInterface
public interface Transaction {
    void modify(TransactionInternal internal);

    private static TransactionInternal createInternal() {
        return new TransactionInternal();
    }

    static void run(Transaction transaction) {
        transaction.modify(createInternal());
    }
}
