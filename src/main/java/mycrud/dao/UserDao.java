package mycrud.dao;

import mycrud.model.User;

import java.util.List;

public interface UserDao {
    List<User> findAll();

    User find(int id);

    void save(User user);

    void update(int id, User updateUser);

    void delete(int id);
}
