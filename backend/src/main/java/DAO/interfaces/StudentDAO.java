package DAO.interfaces;

import model.User;

import java.util.List;
public interface StudentDAO {
    public List<model.Student> getAll();

    public model.Student addStudent(model.Student student, User user);
}
