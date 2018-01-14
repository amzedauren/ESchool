package DAO.impl;

import mappers.UserMapper;
import model.User;

import javax.inject.Inject;
import java.util.List;
import java.util.UUID;

public class UserDAOImpl {

    @Inject
    UserMapper userMapper;

    public User getById(String userId) {
        return this.userMapper.getUserById(userId);
    }

    public User getByEmail(String email) {
        return userMapper.getUserByEmail(email);
    }

    public Boolean isUserExist(String email) {
        return userMapper.isUserExistByEmail(email);
    }

    public List<User> getAll(){
        return this.userMapper.getAll();
    }

    public User addUser(User user){
        try{
            user.setId(UUID.randomUUID().toString());
            userMapper.addUser(user);
            user.setPassword(null);
        } catch (Exception e) {
            return null;
        }
        return user;
    }

    public User validate(User user) {

        User isExist = getByEmail(user.getEmail()) ;
        if(isExist != null && isExist.getPassword().equals(user.getPassword())) {
            isExist.setPassword(null);
            return isExist;
        }
        return null;
    }

}
