package com.gustavotrizotti.domain.usecases.transaction;

import com.gustavotrizotti.domain.entities.transaction.Transaction;

import java.util.List;
import java.util.Optional;

public class FindTransactionUseCase {

    private TransactionDAO transactionDAO;

    public FindTransactionUseCase(TransactionDAO transactionDAO) {
        this.transactionDAO = transactionDAO;
    }

    public Optional<Transaction> findOne(Integer id) {
        if (id == null)
            throw new IllegalArgumentException("ID cannot be null!");

        return transactionDAO.findOne(id);
    }

    public List<Transaction> findAll() {
        return transactionDAO.findAll();
    }
}
