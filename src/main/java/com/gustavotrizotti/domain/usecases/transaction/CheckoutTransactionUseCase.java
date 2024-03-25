package com.gustavotrizotti.domain.usecases.transaction;

import com.gustavotrizotti.domain.entities.book.Book;
import com.gustavotrizotti.domain.entities.book.BookStatus;
import com.gustavotrizotti.domain.entities.transaction.Transaction;
import com.gustavotrizotti.domain.entities.user.User;
import com.gustavotrizotti.domain.usecases.book.FindBookUseCase;
import com.gustavotrizotti.domain.usecases.book.UpdateBookUseCase;
import com.gustavotrizotti.domain.usecases.user.FindUserUseCase;
import com.gustavotrizotti.domain.usecases.user.UpdateUserUseCase;
import com.gustavotrizotti.domain.usecases.utils.EntityNotFoundException;
import com.gustavotrizotti.domain.usecases.utils.Validator;

public class CheckoutTransactionUseCase {
    private TransactionDAO transactionDAO;
    private FindUserUseCase findUserUseCase;
    private FindBookUseCase findBookUseCase;
    private UpdateUserUseCase updateUserUseCase;
    private UpdateBookUseCase updateBookUseCase;

    public CheckoutTransactionUseCase(TransactionDAO transactionDAO, FindUserUseCase findUserUseCase, FindBookUseCase findBookUseCase, UpdateUserUseCase updateUserUseCase, UpdateBookUseCase updateBookUseCase) {
        this.transactionDAO = transactionDAO;
        this.findUserUseCase = findUserUseCase;
        this.findBookUseCase = findBookUseCase;
        this.updateUserUseCase = updateUserUseCase;
        this.updateBookUseCase = updateBookUseCase;
    }

    public Transaction checkoutBook(String userId, Integer bookId) {
        if (Validator.nullOrEmpty(userId) || bookId == null)
            throw new IllegalArgumentException("User ID and/or Book ID are/is null!");

        Book book = findBookUseCase.findOne(bookId)
                .orElseThrow(() -> new EntityNotFoundException("Cannot find a book with ID " + bookId));
        User user = findUserUseCase.findOne(userId)
                .orElseThrow(() -> new EntityNotFoundException("Cannot find a user with ID " + userId));

        if (book.getBookStatus() == BookStatus.CHECKED_OUT)
            throw new TransactionNotAllowedException("The book with ID " + bookId + " is unavailable!");

        if (!user.isAbleToCheckout())
            throw new TransactionNotAllowedException("The user with ID " + userId + " exceeded out the limit!");

        Transaction checkoutTransaction = new Transaction(book, user, user.getCheckoutTimeLimitInDays());
        Integer transactionId = transactionDAO.create(checkoutTransaction);

        book.setBookStatus(BookStatus.CHECKED_OUT);
        updateBookUseCase.update(book);

        user.increaseNumberOfBooksCheckedOut();
        updateUserUseCase.update(user);

        return transactionDAO.findOne(transactionId).get();
    }
}
