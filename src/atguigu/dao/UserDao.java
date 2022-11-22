package atguigu.dao;


import atguigu.pojo.User;

/**
 *
 * @author: dingdi
 * @date: 2022/7/1 17:45
 */
public interface UserDao {


    /**
     * 根据用户名查询用户信息 验证用户名是否有效
     * @param username 用户名
     * @return 用户信息 如果返回null说明没有用户，反之亦然
     */
    public User queryUserByUsername(String username);

    /**
     * 根据用户名和密码查询用户信息  登录
     * @param username 用户名
     * @param password 密码
     * @return 如果返回null，说明用户名或密码错误，反之亦然
     */
    public User queryUserByUsernameAndPassword(String username,String password);

    /**
     * 保存用户信息 注册
     * @param user 用户
     * @return 返回-1表示操作失败，其他是sql语句影响的行数
     */
    public int saveUser(User user);


}
