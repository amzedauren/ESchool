package model;

import javax.ws.rs.FormParam;
import java.util.List;

public class Group {
    private String id;
    @FormParam("groupName")
    private String groupName;
    private User teacher;
    @FormParam("teacherId")
    private String teacherId;
    private List<Student> students;

    public List<Student> getStudents() {
        return students;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public User getTeacher() {
        return teacher;
    }

    public void setTeacher(User teacher) {
        this.teacher = teacher;
    }
}
