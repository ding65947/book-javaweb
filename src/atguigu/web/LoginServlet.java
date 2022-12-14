package atguigu.web;/**
 * @author DingDi
 * @create 2022-07-04 9:11
 */

import atguigu.pojo.User;
import atguigu.service.UserService;
import atguigu.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 * @author: dingdi
 * @date: 2022/7/4 9:11
 */
public class LoginServlet extends HttpServlet {

    private UserService userService=new UserServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        //调用userServlet.login()处理登录业务
        User loginUser = userService.login(new User(null, username, password, null));

        if(loginUser==null){
            //把错误信息，和回显的表单项信息，保存到request域中
            req.setAttribute("msg", "用户名或密码错误！");
            req.setAttribute("username",username);
            //跳回登录页面
            System.out.println("用户名或密码错误，登录失败！");
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req, resp);
        }else {
            //登录成功
            System.out.println("登录成功！");
            req.getRequestDispatcher("/pages/user/login_success.jsp").forward(req, resp);
        }

    }
}
