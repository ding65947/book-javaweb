package atguigu.web;/**
 * @author DingDi
 * @create 2022-07-02 23:09
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
 * @date: 2022/7/2 23:09
 */
public class RegistServlet extends HttpServlet {
    private UserService userService=new UserServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求的参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String code = req.getParameter("code");

        //检查  验证码是否正确       写死，要求验证码为abcde
        if("abcde".equalsIgnoreCase(code)){
            //正确，检查用户名是否可用
            if(userService.existsUsername(username)){
                //不可用，跳回注册页面
                System.out.println("用户名["+username+"]已存在！");
                //把回显信息保存到request域中
                req.setAttribute("msg", "用户名已存在！");
                req.setAttribute("username", username);
                req.setAttribute("email", email);


                req.getRequestDispatcher("/pages/user/regist.jsp").forward(req, resp);
            }else {
                //可用，调用service保存到数据库
                userService.registUser(new User(null,username,password,email));
                //跳到注册成功界面
                req.getRequestDispatcher("/pages/user/regist_success.jsp").forward(req, resp);
            }

        }else{
            //把回显信息保存到request域中
            req.setAttribute("msg", "验证码错误！");
            req.setAttribute("username", username);
            req.setAttribute("email", email);

            //不正确，跳回注册页面
            System.out.println("验证码["+code+"]错误");
            req.getRequestDispatcher("/pages/user/regist.jsp").forward(req, resp);
        }

    }
}
