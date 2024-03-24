package com.gustavotrizotti.domain.usecases.book;

import com.gustavotrizotti.domain.entities.book.Book;
import com.gustavotrizotti.domain.usecases.utils.EntityAlreadyExistsException;
import com.gustavotrizotti.domain.usecases.utils.Notification;
import com.gustavotrizotti.domain.usecases.utils.Validator;

public class CreateBookUseCase {
    private BookDAO bookDAO;

    public CreateBookUseCase(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    public Integer insert(Book book) {
        Validator<Book> bookValidator = new BookInputRequestValidator();
        Notification notification = bookValidator.validate(book);

        if (notification.hasErrors())
            throw new IllegalArgumentException(notification.errorMessage());

        String isbn = book.getISBN();

        if (bookDAO.findByISBN(isbn).isPresent())
            throw new EntityAlreadyExistsException("This ISBN is already registered!");

        return bookDAO.create(book);
    }

}
