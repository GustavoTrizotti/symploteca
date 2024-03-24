package com.gustavotrizotti.domain.usecases.book;

import com.gustavotrizotti.domain.entities.book.Book;
import com.gustavotrizotti.domain.usecases.utils.DAO;

import java.util.Optional;

public interface BookDAO extends DAO<Book, Integer> {
    Optional<Book> findByISBN(String ISBN);


}
