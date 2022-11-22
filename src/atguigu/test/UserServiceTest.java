package atguigu.test;

import atguigu.pojo.User;
import atguigu.service.UserService;
import atguigu.service.impl.UserServiceImpl;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author DingDi
 * @create 2022-07-02 22:49
 */
public class UserServiceTest {

    UserService userService=new UserServiceImpl();
    @Test
    public void registUser() {
        userService.registUser(new User(null,"bbj168","6666","bbj168@qq.com"));
        userService.registUser(new User(null,"abc168","6666","abc168@qq.com"));
    }

    @Test
    public void login() {
        System.out.println(userService.login(new User(null,"abc168","6667",null)));
    }

    @Test
    public void existsUsername() {
        if(userService.existsUsername("abc168")){
            System.out.println("用户名已存在！");
        }else {
            System.out.println("用户名可用！");
        }
    }
}