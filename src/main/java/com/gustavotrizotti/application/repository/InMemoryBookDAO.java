package com.gustavotrizotti.application.repository;

import com.gustavotrizotti.domain.entities.book.Book;
import com.gustavotrizotti.domain.usecases.book.BookDAO;

import java.util.*;

public class InMemoryBookDAO implements BookDAO {

    private static final Map<Integer, Book> DB = new LinkedHashMap<>();
    private static int idCounter;

    @Override
    public Integer create(Book book) {
        idCounter++;
        book.setId(idCounter);
        DB.put(idCounter, book);
        return idCounter;
    }

    @Override
    public Optional<Book> findOne(Integer key) {
        if (DB.containsKey(key))
            return Optional.of(DB.get(key));
        return Optional.empty();
    }

    @Override
    public Optional<Book> findByISBN(String ISBN) {
        return DB.values().stream()
                .filter(b -> b.getISBN().equals(ISBN))
                .findAny();
    }

    @Override
    public List<Book> findAll() {
        return new ArrayList<Book>(DB.values());
    }

    @Override
    public boolean update(Book book) {
        Integer id = book.getId();
        if (DB.containsKey(id)) {
            DB.replace(id, book);
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
    public boolean delete(Book book) {
        return deleteByKey(book.getId());
    }
}
