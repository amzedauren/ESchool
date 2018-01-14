package DAO.impl;

import DAO.interfaces.GroupDAO;
import mappers.GroupMapper;
import mappers.UserMapper;
import model.Group;

import javax.inject.Inject;
import java.util.List;
import java.util.UUID;

public class GroupDAOImpl implements GroupDAO{

    @Inject
    GroupMapper groupMapper;
    @Inject
    UserMapper userMapper;

    public List<Group> getAll() {
        return this.groupMapper.getAll();
    }

    public Group addGroup(Group group) {
        try {
            group.setId(UUID.randomUUID().toString());
            this.groupMapper.addGroup(group);
            group.setTeacher(this.userMapper.getUserById(group.getTeacherId()));
        } catch (Exception e) {
            System.out.println(e.toString());
            return null;
        }
        return group;
    }

    public Boolean addStudent(String groupId, String studentId) {
        try {
            Boolean isExist = this.groupMapper.isGroupExistWithStudent(groupId, studentId);
            if(isExist)
                return false;
            this.groupMapper.addGroupStudent(UUID.randomUUID().toString(), groupId, studentId);
            return true;

        } catch (Exception e) {
            return false;
        }
    }

    public Boolean removeStudent(String groupId, String studentId) {
        try{
            this.groupMapper.removeStudent(groupId, studentId);
            return true;
        } catch (Exception e) {
            return false;
        }
    }



}
