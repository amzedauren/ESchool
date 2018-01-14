package mappers;

import model.Student;

import model.User;
import org.apache.ibatis.annotations.*;
import org.mybatis.cdi.Mapper;

import java.util.List;

@Mapper
public interface StudentMapper {

    @Select("SELECT * FROM public.e_school_student WHERE id = #{studentId}")
    @Results({
            @Result(property = "phoneNumber", column = "phone_number")
    })
    Student getStudentById(@Param("studentId") String studentId);

    @Select("SELECT * FROM public.e_school_student as s inner join public.e_school_groups_students as gs on s.id = gs.student_id  " +
            "WHERE gs.group_id = #{groupId}")
    List<Student> getStudentsByGroupId(@Param("groupId") String groupId);

    @Select("SELECT * FROM public.e_school_student")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "phoneNumber", column = "phone_number"),
            @Result(property = "addedBy", column = "added_by", javaType=User.class,
                    one=@One(select="mappers.UserMapper.getUserById")),
            @Result(property="groups", column="id", javaType= List.class,
                    many=@Many(select="mappers.GroupMapper.getGroupsByStudentId"))
    })
    List<Student> getAll();

    @Insert("Insert into public.e_school_student (id, name, email, phone_number, added_by) VALUES " +
            "(#{student.id}, #{student.name}, #{student.email}, #{student.phoneNumber}, #{student.addedBy.id})")
    void addStudent(@Param("student") Student student);




}
