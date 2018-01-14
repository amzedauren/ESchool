package DAO.interfaces;

import model.Group;

import java.util.List;

public interface GroupDAO {
    public List<Group> getAll();

    public Group addGroup(Group group);

    public Boolean addStudent(String groupId, String studentId);

    public Boolean removeStudent(String groupId, String studentId);

}
