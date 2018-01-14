package DAO.interfaces;

import model.User;

import java.util.List;

public interface UserDAO {
    public User getById(String userId);

    public User getByEmail(String email);

    public Boolean isUserExist(String email);

    public List<User> getAll();

    public User addUser(User user);

    public User validate(User user);
}
