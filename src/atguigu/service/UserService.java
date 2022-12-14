package atguigu.service;/**
 * @author DingDi
 * @create 2022-07-02 11:43
 */

import atguigu.pojo.User;

/**
 *
 * @author: dingdi
 * @date: 2022/7/2 11:43
 */
public interface UserService {


    /**
     * 注册用户
     * @param user
     */
    public void registUser(User user);

    /**
     * 登录用户
     * @param user
     * @return 如果返回null表示登录失败，返回有值是登录成功
     */
    public User login(User user);

    /**
     * 检查用户名是否可用
     * @param username
     * @return 返回true 表示用户名已存在，返回false表示用户名可用
     */
    public boolean existsUsername(String username);
}
