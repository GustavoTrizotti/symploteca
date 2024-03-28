package com.gustavotrizotti;

import com.gustavotrizotti.application.repository.InMemoryBookDAO;
import com.gustavotrizotti.application.repository.InMemoryTransactionDAO;
import com.gustavotrizotti.application.repository.InMemoryUserDAO;
import com.gustavotrizotti.domain.entities.book.Book;
import com.gustavotrizotti.domain.entities.book.BookGender;
import com.gustavotrizotti.domain.entities.book.BookStatus;
import com.gustavotrizotti.domain.entities.user.Faculty;
import com.gustavotrizotti.domain.entities.user.Student;
import com.gustavotrizotti.domain.entities.user.User;
import com.gustavotrizotti.domain.usecases.book.*;
import com.gustavotrizotti.domain.usecases.transaction.CheckoutTransactionUseCase;
import com.gustavotrizotti.domain.usecases.transaction.FindTransactionUseCase;
import com.gustavotrizotti.domain.usecases.transaction.ReturnTransactionUseCase;
import com.gustavotrizotti.domain.usecases.transaction.TransactionDAO;
import com.gustavotrizotti.domain.usecases.user.*;

public class Main {
    // Book UseCases
    private static CreateBookUseCase createBookUseCase;
    private static UpdateBookUseCase updateBookUseCase;
    private static FindBookUseCase findBookUseCase;
    private static RemoveBookUseCase removeBookUseCase;

    // User UseCases
    private static CreateUserUseCase createUserUseCase;
    private static UpdateUserUseCase updateUserUseCase;
    private static FindUserUseCase findUserUseCase;
    private static RemoveUserUseCase removeUserUseCase;

    // Transaction UseCases
    private static CheckoutTransactionUseCase checkoutTransactionUseCase;
    private static ReturnTransactionUseCase returnTransactionUseCase;
    private static FindTransactionUseCase findTransactionUseCase;

    public static void main(String[] args) {
        configureInjection();

        User user1 = new Student("SC001", "Zé de Votuporanga", "zedevot@email.com", "16999000", "BES");
        User user2 = new Faculty("SC002", "João de Boituva", "jaodeboituva@email.com", "16999123", "CTI");
        createUserUseCase.insert(user1);
        createUserUseCase.insert(user2);

        Book book1 = new Book(1, 300, "Clean Code", "Robert C. Martin", "IBMooks", "01-1", BookGender.TECHNICAL, BookStatus.AVAILABLE);
        Book book2 = new Book(3, 120, "Clean Architecture", "Robert C. Martin", "IBMooks", "02-2", BookGender.TECHNICAL, BookStatus.AVAILABLE);
        Book book3 = new Book(2, 156, "Principia Mathematica", "Sir. Isaac Newton", "Newton", "03-3", BookGender.SCIENCE, BookStatus.AVAILABLE);
        Book book4 = new Book(2, 87, "13 Colonies - An American Story", "John Doe & Alex Andersen", "Intra Books", "04-4", BookGender.HISTORY, BookStatus.AVAILABLE);
        Book book5 = new Book(4, 310, "Nyxia 2: Unleashed", "Emmet Atwatter", "ISPM", "05-5", BookGender.DRAMA, BookStatus.AVAILABLE);
        Book book6 = new Book(7, 112, "Where's Monday?", "Julian Hichter", "Alto Productions", "06-6", BookGender.ACTION, BookStatus.AVAILABLE);

        Integer idBook1 = createBookUseCase.insert(book1);
        Integer idBook2 = createBookUseCase.insert(book2);
        Integer idBook3 = createBookUseCase.insert(book3);
        Integer idBook4 = createBookUseCase.insert(book4);
        Integer idBook5 = createBookUseCase.insert(book5);
        Integer idBook6 = createBookUseCase.insert(book6);

        checkoutBook(user2.getInstitutionalId(), idBook1);
        checkoutBook(user2.getInstitutionalId(), idBook2);
        checkoutBook(user2.getInstitutionalId(), idBook3);
        checkoutBook(user2.getInstitutionalId(), idBook4);
        checkoutBook(user2.getInstitutionalId(), idBook5);
        checkoutBook(user2.getInstitutionalId(), idBook6);

        returnBook(idBook1);
        returnBook(idBook2);
        returnBook(idBook3);
        returnBook(idBook4);
        returnBook(idBook5);
        returnBook(idBook6);

        findTransactionUseCase.findAll().stream().forEach(transaction -> System.out.println(transaction));
    }

    public static void checkoutBook(String userId, Integer bookId) {
        try {
            checkoutTransactionUseCase.checkoutBook(userId, bookId);
            System.out.println("Book has been checked out!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void returnBook(Integer bookId) {
        try {
            returnTransactionUseCase.returnBook(bookId);
            System.out.println("Book returned successfully!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void configureInjection() {
        BookDAO bookDAO = new InMemoryBookDAO();
        createBookUseCase = new CreateBookUseCase(bookDAO);
        updateBookUseCase = new UpdateBookUseCase(bookDAO);
        findBookUseCase = new FindBookUseCase(bookDAO);
        removeBookUseCase = new RemoveBookUseCase(bookDAO);

        UserDAO userDAO = new InMemoryUserDAO();
        createUserUseCase = new CreateUserUseCase(userDAO);
        updateUserUseCase = new UpdateUserUseCase(userDAO);
        findUserUseCase = new FindUserUseCase(userDAO);
        removeUserUseCase = new RemoveUserUseCase(userDAO);

        TransactionDAO transactionDAO = new InMemoryTransactionDAO();
        checkoutTransactionUseCase = new CheckoutTransactionUseCase(transactionDAO, findUserUseCase, findBookUseCase, updateUserUseCase, updateBookUseCase);
        returnTransactionUseCase = new ReturnTransactionUseCase(transactionDAO, findUserUseCase, findBookUseCase, updateUserUseCase, updateBookUseCase);
        findTransactionUseCase = new FindTransactionUseCase(transactionDAO);

    }
}