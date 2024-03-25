package com.gustavotrizotti.domain.usecases.transaction;

public class TransactionNotAllowedException extends RuntimeException{
    public TransactionNotAllowedException(String message) {
        super(message);
    }
}
