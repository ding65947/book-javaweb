package atguigu.test;

import atguigu.dao.impl.UserDaoImpl;
import atguigu.pojo.User;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author DingDi
 * @create 2022-07-02 11:04
 */
public class UserDaoTest {
    UserDaoImpl userDao = new UserDaoImpl();
    @Test
    public void queryUserByUsername() {
        if(userDao.queryUserByUsername("admin123")==null){
            System.out.println("用户名可用！");
        }else{
            System.out.println("用户名已存在！");
        }
    }

    @Test
    public void queryUserByUsernameAndPassword() {
        if(userDao.queryUserByUsernameAndPassword("admin","admin12")==null){
            System.out.println("用户名或密码错误，登录失败！");
        }else{
            System.out.println("登录成功！");
        }
    }

    @Test
    public void saveUser() {
        System.out.println(userDao.saveUser(new User(null,"admin2","123456","dingd168@qq.com")));
    }
}