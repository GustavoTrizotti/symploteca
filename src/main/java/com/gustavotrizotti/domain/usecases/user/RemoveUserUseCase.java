package com.gustavotrizotti.domain.usecases.user;

import com.gustavotrizotti.domain.entities.book.Book;
import com.gustavotrizotti.domain.entities.user.User;
import com.gustavotrizotti.domain.usecases.utils.EntityNotFoundException;
import com.gustavotrizotti.domain.usecases.utils.Validator;

public class RemoveUserUseCase {
    private UserDAO userDAO;

    public RemoveUserUseCase(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public boolean remove(String id) {
        if (Validator.nullOrEmpty(id) || userDAO.findOne(id).isEmpty())
            throw new EntityNotFoundException("Book not found!");
        return userDAO.deleteByKey(id);
    }

    public boolean remove(User user) {
        String id = user.getInstitutionalId();
        if (id == null || userDAO.findOne(id).isEmpty())
            throw new EntityNotFoundException("User not found!");
        return userDAO.delete(user);
    }
}
