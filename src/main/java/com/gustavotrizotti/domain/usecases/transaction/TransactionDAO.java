package com.gustavotrizotti.domain.usecases.transaction;

import com.gustavotrizotti.domain.entities.transaction.Transaction;
import com.gustavotrizotti.domain.usecases.utils.DAO;

import java.util.Optional;

public interface TransactionDAO extends DAO<Transaction, Integer> {
    Optional<Transaction> findTransactionByBookId(Integer id);

}
