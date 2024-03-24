package com.gustavotrizotti.domain.usecases.user;

import com.gustavotrizotti.domain.entities.book.Book;
import com.gustavotrizotti.domain.entities.user.User;
import com.gustavotrizotti.domain.usecases.utils.EntityNotFoundException;
import com.gustavotrizotti.domain.usecases.utils.Notification;
import com.gustavotrizotti.domain.usecases.utils.Validator;

public class UpdateUserUseCase {
    private UserDAO userDAO;

    public UpdateUserUseCase(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public boolean update(User user) {
        Validator<User> userValidator = new UserInputRequestValidator();
        Notification notification = userValidator.validate(user);

        if (notification.hasErrors())
            throw new IllegalArgumentException(notification.errorMessage());

        String id = user.getInstitutionalId();

        if (userDAO.findOne(id).isEmpty())
            throw new EntityNotFoundException("User not found!");

        return userDAO.update(user);
    }

}
