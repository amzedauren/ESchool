package DAO.impl;

import mappers.StudentMapper;
import model.Student;
import model.User;

import javax.inject.Inject;
import java.util.List;
import java.util.UUID;

public class StudentDAOImpl {

    @Inject
    StudentMapper studentMapper;

    public List<Student> getAll(){
        return this.studentMapper.getAll();
    }

    public Student addStudent(Student student, User user){
        try{
            student.setId(UUID.randomUUID().toString());
            student.setAddedBy(user);
            this.studentMapper.addStudent(student);

        } catch (Exception e) {
            return null;
        }
        return student;
    }

}
