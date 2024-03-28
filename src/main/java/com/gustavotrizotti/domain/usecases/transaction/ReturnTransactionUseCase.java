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

import java.time.LocalDate;

public class ReturnTransactionUseCase {
    private TransactionDAO transactionDAO;
    private FindUserUseCase findUserUseCase;
    private FindBookUseCase findBookUseCase;
    private UpdateUserUseCase updateUserUseCase;
    private UpdateBookUseCase updateBookUseCase;

    public ReturnTransactionUseCase(TransactionDAO transactionDAO, FindUserUseCase findUserUseCase, FindBookUseCase findBookUseCase, UpdateUserUseCase updateUserUseCase, UpdateBookUseCase updateBookUseCase) {
        this.transactionDAO = transactionDAO;
        this.findUserUseCase = findUserUseCase;
        this.findBookUseCase = findBookUseCase;
        this.updateUserUseCase = updateUserUseCase;
        this.updateBookUseCase = updateBookUseCase;
    }

    public void returnBook(Integer bookId) {
        if (bookId == null)
            throw new IllegalArgumentException("Book ID is null!");

        Transaction transaction = transactionDAO.findTransactionByBookId(bookId)
                .orElseThrow(() -> new EntityNotFoundException("Cannot find an open transaction for book ID " + bookId));

        transaction.setReturnDate(LocalDate.now());
        transactionDAO.update(transaction);
        Book book = findBookUseCase.findOne(bookId).get();
        book.setBookStatus(BookStatus.AVAILABLE);
        updateBookUseCase.update(book);

        String userId = transaction.getUser().getInstitutionalId();
        User user = findUserUseCase.findOne(userId).get();
        user.decreaseNumberOfBooksCheckedOut();
        updateUserUseCase.update(user);

    }
}
