package atguigu.service.impl;/**
 * @author DingDi
 * @create 2022-07-02 11:48
 */

import atguigu.dao.UserDao;
import atguigu.dao.impl.UserDaoImpl;
import atguigu.pojo.User;
import atguigu.service.UserService;

/**
 *
 * @author: dingdi
 * @date: 2022/7/2 11:48
 */
public class UserServiceImpl implements UserService {

    private UserDao userDao=new UserDaoImpl();


    @Override
    public void registUser(User user) {
        userDao.saveUser(user);
    }

    @Override
    public User login(User user) {
        return userDao.queryUserByUsernameAndPassword(user.getUsername(),user.getPassword());
    }

    @Override
    public boolean existsUsername(String username) {
        if(userDao.queryUserByUsername(username)==null){
            //等于null,说明没查到，没查到就是可用
            return false;
        }
        return true;
    }
}
