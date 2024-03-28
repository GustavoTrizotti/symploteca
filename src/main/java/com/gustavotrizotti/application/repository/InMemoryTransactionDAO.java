package com.gustavotrizotti.application.repository;

import com.gustavotrizotti.domain.entities.transaction.Transaction;
import com.gustavotrizotti.domain.usecases.transaction.TransactionDAO;

import java.util.*;

public class InMemoryTransactionDAO implements TransactionDAO {

    private static final Map<Integer, Transaction> DB = new LinkedHashMap<>();
    private static int idCounter;

    @Override
    public Integer create(Transaction transaction) {
        idCounter++;
        transaction.setId(idCounter);
        DB.put(idCounter, transaction);
        return idCounter;
    }

    @Override
    public Optional<Transaction> findTransactionByBookId(Integer id) {
        return DB.values().stream()
                .filter(transaction -> transaction.getBook().getId() == id)
                .findAny();
    }

    @Override
    public Optional<Transaction> findOne(Integer key) {
        if (DB.containsKey(key))
            return Optional.of(DB.get(key));
        return Optional.empty();
    }

    @Override
    public List<Transaction> findAll() {
        return new ArrayList<Transaction>(DB.values());
    }

    @Override
    public boolean update(Transaction transaction) {
        Integer id = transaction.getId();
        if (DB.containsKey(id)) {
            DB.replace(id, transaction);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteByKey(Integer key) {
        if (DB.containsKey(key)) {
            DB.remove(key);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(Transaction transaction) {
        return deleteByKey(transaction.getId());
    }
}
