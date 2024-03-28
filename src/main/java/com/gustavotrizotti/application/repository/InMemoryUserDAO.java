package com.gustavotrizotti.application.repository;

import com.gustavotrizotti.domain.entities.user.User;
import com.gustavotrizotti.domain.usecases.user.UserDAO;

import java.util.*;

public class InMemoryUserDAO implements UserDAO {

    private static final Map<String, User> DB = new LinkedHashMap<>();

    @Override
    public String create(User user) {
        DB.put(user.getInstitutionalId(), user);
        return user.getInstitutionalId();
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return DB.values().stream()
                .filter(user -> user.getEmail().equals(email))
                .findAny();
    }

    @Override
    public Optional<User> findOne(String key) {
        if (DB.containsKey(key))
            return Optional.of(DB.get(key));
        return Optional.empty();
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<User>(DB.values());
    }

    @Override
    public boolean update(User user) {
        String id = user.getInstitutionalId();
        if (DB.containsKey(id)) {
            DB.replace(id, user);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteByKey(String key) {
        if (DB.containsKey(key)) {
            DB.remove(key);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(User user) {
        return deleteByKey(user.getInstitutionalId());
    }

}
