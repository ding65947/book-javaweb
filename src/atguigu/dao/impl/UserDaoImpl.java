package atguigu.dao.impl;/**
 * @author DingDi
 * @create 2022-07-02 10:52
 */

import atguigu.dao.UserDao;
import atguigu.pojo.User;

/**
 *
 * @author: dingdi
 * @date: 2022/7/2 10:52
 */
public class UserDaoImpl extends BaseDao implements UserDao {

    @Override
    public User queryUserByUsername(String username) {
        String sql="select `id`,`username`,`password`,`email` from t_user where username=?";
        return queryForOne(User.class,sql,username);
    }

    @Override
    public User queryUserByUsernameAndPassword(String username, String password) {
        String sql="select `id`,`username`,`password`,`email` from t_user where username=? and password=?";
        return queryForOne(User.class,sql,username,password);
    }

    @Override
    public int saveUser(User user) {
        String sql="insert into t_user(`username`,`password`,`email`) values (?,?,?)";
        return update(sql,user.getUsername(),user.getPassword(),user.getEmail());
    }
}
