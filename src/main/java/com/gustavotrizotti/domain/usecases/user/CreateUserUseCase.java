package com.gustavotrizotti.domain.usecases.user;

import com.gustavotrizotti.domain.entities.user.User;
import com.gustavotrizotti.domain.usecases.utils.EntityAlreadyExistsException;
import com.gustavotrizotti.domain.usecases.utils.Notification;
import com.gustavotrizotti.domain.usecases.utils.Validator;

public class CreateUserUseCase {
    private UserDAO userDAO;

    public CreateUserUseCase(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public String insert(User user) {
        Validator<User> userValidator = new UserInputRequestValidator();
        Notification notification = userValidator.validate(user);

        if (notification.hasErrors())
            throw new IllegalArgumentException(notification.errorMessage());

        String email = user.getEmail();

        if (userDAO.findByEmail(email).isPresent())
            throw new EntityAlreadyExistsException("This email is already registered!");

        return userDAO.create(user);
    }

}
