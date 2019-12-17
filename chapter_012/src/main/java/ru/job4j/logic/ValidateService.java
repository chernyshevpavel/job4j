package ru.job4j.logic;

import ru.job4j.persistent.DbStore;
import ru.job4j.models.User;
import ru.job4j.persistent.Store;

import java.util.List;

/**
 * singleton from https://habr.com/ru/post/129494/
 */
public class ValidateService {

    private final static ValidateService INSTANCE = new ValidateService();

    private ValidateService() {

    }

    public static ValidateService getINSTANCE() {
        return INSTANCE;
    }

    private final Store memoryStore = DbStore.getINSTANCE();

    private void checkUserId(int id) throws IllegalArgumentException {
        if (id <= 0) {
            throw new IllegalArgumentException("Id user is not correct");
        }
    }

    public boolean add(User user) {
        if (user.getName().length() == 0) {
            throw new IllegalArgumentException("User name is not correct");
        }
        return this.memoryStore.add(user);
    }

    public boolean update(User user) {
        checkUserId(user.getId());
        return this.memoryStore.update(user);
    }

    public List<User> findAll() {
        return this.memoryStore.findAll();
    }

    public User findById(int id) {
        this.checkUserId(id);
        return this.memoryStore.findById(id);
    }

    public boolean delete(User user) {
        this.checkUserId(user.getId());
        return this.memoryStore.delete(user.getId());
    }
}
