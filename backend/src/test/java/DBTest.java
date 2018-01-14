import eschool.SqlSessionFactoryProvider;
import mappers.UserMapper;
import model.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.validation.constraints.AssertTrue;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import java.util.UUID;

public class DBTest {

    @Test
    public void test(){

        try {
            File config = new File("./src/main/webapp/WEB-INF/mybatis-config.xml");
//            System.out.println(config.getAbsolutePath());
//            System.out.println(config.exists());

            InputStream inputStream = new FileInputStream(config);

            SqlSessionFactory sqlSessionFactory =
                    new SqlSessionFactoryBuilder().build(inputStream);

            SqlSessionFactory ssf = sqlSessionFactory;

            UserMapper um = ssf.openSession().getMapper(UserMapper.class);

            User user = new User();
            user.setId(UUID.randomUUID().toString());
            user.setName("testPerson");
            user.setEmail("testPerson@testPerson");
            user.setPassword("test");
            um.addUser(user);
            Boolean exist = um.isUserExistByEmail(user.getEmail());
            Assert.assertTrue(exist);
            if(exist) {
                um.deleteUser(user.getId());
                exist = um.isUserExistByEmail(user.getEmail());
                Assert.assertFalse(exist);
            }

        } catch (Exception e) {
            System.out.println(e.toString());
            Assert.assertTrue(false);
        }

        System.out.println("db test finished");
    }
}
