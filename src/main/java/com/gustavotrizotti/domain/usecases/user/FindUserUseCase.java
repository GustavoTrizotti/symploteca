package com.gustavotrizotti.domain.usecases.user;

import com.gustavotrizotti.domain.entities.book.Book;
import com.gustavotrizotti.domain.entities.user.User;
import com.gustavotrizotti.domain.usecases.utils.Validator;

import java.util.List;
import java.util.Optional;

public class FindUserUseCase {
    private UserDAO userDAO;

    public FindUserUseCase(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public Optional<User> findOne(String id) {
        if (Validator.nullOrEmpty(id))
            throw new IllegalArgumentException("ID cannot be null!");

        return userDAO.findOne(id);
    }

    public Optional<User> findByEmail(String email) {
        if (Validator.nullOrEmpty(email))
            throw new IllegalArgumentException("Email cannot be null or empty!");

        return userDAO.findByEmail(email);
    }

    public List<User> findAll() {
        return userDAO.findAll();
    }
}