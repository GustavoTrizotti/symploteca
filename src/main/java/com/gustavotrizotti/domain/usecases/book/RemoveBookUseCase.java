package com.gustavotrizotti.domain.usecases.book;

import com.gustavotrizotti.domain.entities.book.Book;
import com.gustavotrizotti.domain.usecases.utils.EntityNotFoundException;

public class RemoveBookUseCase {
    private BookDAO bookDAO;

    public RemoveBookUseCase(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    public boolean remove(Integer id) {
        if (id == null || bookDAO.findOne(id).isEmpty())
            throw new EntityNotFoundException("Book not found!");
        return bookDAO.deleteByKey(id);
    }

    public boolean remove(Book book) {
        Integer id = book.getId();
        if (id == null || bookDAO.findOne(id).isEmpty())
            throw new EntityNotFoundException("Book not found!");
        return bookDAO.delete(book);
    }
}
