package com.gustavotrizotti.domain.usecases.user;

import com.gustavotrizotti.domain.entities.book.Book;
import com.gustavotrizotti.domain.entities.user.User;
import com.gustavotrizotti.domain.usecases.utils.DAO;

import java.util.Optional;

public interface UserDAO extends DAO<User, String> {
    Optional<User> findByEmail(String email);

}
