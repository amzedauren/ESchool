package mappers;

import model.Group;
import model.Student;
import model.User;
import org.apache.ibatis.annotations.*;
import org.mybatis.cdi.Mapper;

import java.util.List;

@Mapper
public interface GroupMapper {

//    @Select("SELECT * FROM public.e_school_group WHERE id = #{groupId}")
//    @Results({
//            @Result(property = "id", column = "id"),
//            @Result(property = "groupName", column = "group_name"),
//    })
//    StudentDAO getGroupById(@Param("groupId") String groupId);


    @Select("SELECT * FROM public.e_school_group as g inner join public.e_school_groups_students as gs on g.id = gs.group_id  " +
            "WHERE gs.student_id = #{studentId}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "groupName", column = "group_name"),

    })
    List<Group> getGroupsByStudentId(@Param("studentId") String studentId);

    @Select("SELECT * FROM public.e_school_group")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "groupName", column = "group_name"),
            @Result(property = "teacher", column = "teacher_id", javaType=User.class,
                    one=@One(select="mappers.UserMapper.getUserById")),
            @Result(property="students", column="id", javaType= List.class,
                    many=@Many(select="mappers.StudentMapper.getStudentsByGroupId"))
    })
    List<Group> getAll();


    @Insert("Insert into public.e_school_group (id, group_name, teacher_id) VALUES " +
            "(#{group.id}, #{group.groupName}, #{group.teacherId})")
    void addGroup(@Param("group") Group group);


    @Select("select exists(select 1 from public.e_school_groups_students " +
            "where group_id=#{groupId} and student_id=#{studentId})")
    Boolean isGroupExistWithStudent(@Param("groupId") String groupId, @Param("studentId") String studentId);

    @Insert("Insert into public.e_school_groups_students (id, group_id, student_id) VALUES " +
            "(#{id}, #{groupId}, #{studentId})")
    void addGroupStudent(@Param("id") String id, @Param("groupId") String groupId, @Param("studentId") String studentId);

    @Delete("Delete from public.e_school_groups_students where group_id=#{groupId} and student_id=#{studentId}")
    void removeStudent(@Param("groupId") String groupId, @Param("studentId") String studentId);


}
