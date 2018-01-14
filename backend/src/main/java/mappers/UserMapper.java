package mappers;
import model.Group;
import model.User;
import org.apache.ibatis.annotations.*;
import org.mybatis.cdi.Mapper;
import org.postgresql.util.PSQLException;

import java.util.List;

//do not pay attention if your IDE arguing for annotaions, it is mybatis-cdi fail
@Mapper
public interface UserMapper{

    @Select("SELECT id, name, email FROM public.e_school_user WHERE id = #{userId}")
    User getUserById(@Param("userId") String userId);

    @Select("SELECT * FROM public.e_school_user WHERE email = #{email}")
    User getUserByEmail(@Param("email") String email);

    @Select("SELECT id, name, email FROM public.e_school_user")
    List<User> getAll();

    @Insert("Insert into public.e_school_user (id, name, email, password) VALUES " +
            "(#{user.id}, #{user.name}, #{user.email}, #{user.password})")
    void addUser(@Param("user") User user);

    @Select("select exists(select 1 from public.e_school_user where email=#{email})")
    Boolean isUserExistByEmail(@Param("email") String email);

    @Delete("Delete from public.e_school_user where id=#{userId}")
    void deleteUser(@Param("userId") String userId);


}