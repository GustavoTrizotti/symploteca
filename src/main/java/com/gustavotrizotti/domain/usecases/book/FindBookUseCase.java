package com.gustavotrizotti.domain.usecases.book;

import com.gustavotrizotti.domain.entities.book.Book;
import com.gustavotrizotti.domain.usecases.utils.Validator;

import java.util.List;
import java.util.Optional;

public class FindBookUseCase {
    private BookDAO bookDAO;

    public FindBookUseCase(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    public Optional<Book> findOne(Integer id) {
        if (id == null)
            throw new IllegalArgumentException("ID cannot be null!");

        return bookDAO.findOne(id);
    }

    public Optional<Book> findByISBN(String ISBN) {
        if (Validator.nullOrEmpty(ISBN))
            throw new IllegalArgumentException("ISBN cannot be null or empty!");

        return bookDAO.findByISBN(ISBN);
    }

    public List<Book> findAll() {
        return bookDAO.findAll();
    }
}