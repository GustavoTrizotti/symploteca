package com.gustavotrizotti.domain.usecases.book;

import com.gustavotrizotti.domain.entities.book.Book;
import com.gustavotrizotti.domain.usecases.utils.EntityAlreadyExistsException;
import com.gustavotrizotti.domain.usecases.utils.EntityNotFoundException;
import com.gustavotrizotti.domain.usecases.utils.Notification;
import com.gustavotrizotti.domain.usecases.utils.Validator;

public class UpdateBookUseCase {
    private BookDAO bookDAO;

    public UpdateBookUseCase(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    public boolean update(Book book) {
        Validator<Book> bookValidator = new BookInputRequestValidator();
        Notification notification = bookValidator.validate(book);

        if (notification.hasErrors())
            throw new IllegalArgumentException(notification.errorMessage());

        Integer id = book.getId();

        if (bookDAO.findOne(id).isEmpty())
            throw new EntityNotFoundException("This book doesn't exists!");

        return bookDAO.update(book);
    }

}
