package atguigu.web;/**
 * @author DingDi
 * @create 2022-07-06 22:05
 */

import atguigu.pojo.User;
import atguigu.service.UserService;
import atguigu.service.impl.UserServiceImpl;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

/**
 *
 * @author: dingdi
 * @date: 2022/7/6 22:05
 */
public class UserServlet extends BaseSevlet {

    private UserService userService = new UserServiceImpl();


    /**
     * 处理登录业务
     * @param req 请求
     * @param resp 响应
     * @throws ServletException
     * @throws IOException
     */
    protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //获取请求参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        //调用userServlet.login()处理登录业务
        User loginUser = userService.login(new User(null, username, password, null));

        if (loginUser == null) {
            //把错误信息，和回显的表单项信息，保存到request域中
            req.setAttribute("msg", "用户名或密码错误！");
            req.setAttribute("username", username);
            //跳回登录页面
            System.out.println("用户名或密码错误，登录失败！");
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req, resp);
        } else {
            //登录成功

            //保存用户信息到session域中
            req.getSession().setAttribute("user",loginUser);

            System.out.println("登录成功！");
            req.getRequestDispatcher("/pages/user/login_success.jsp").forward(req, resp);
        }

    }


    /**
     * 处理注册业务
     * @param req 请求
     * @param resp 响应
     * @throws ServletException
     * @throws IOException
     */
    protected void regist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取session中的验证码
        String token = (String) req.getSession().getAttribute(KAPTCHA_SESSION_KEY);
        //删除session中的验证码
        req.getSession().removeAttribute(KAPTCHA_SESSION_KEY);


        //获取请求的参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String code = req.getParameter("code");

        //检查  验证码是否正确       写死，要求验证码为abcde
        if (token!=null && token.equalsIgnoreCase(code)) {
            //正确，检查用户名是否可用
            if (userService.existsUsername(username)) {
                //不可用，跳回注册页面
                System.out.println("用户名[" + username + "]已存在！");
                //把回显信息保存到request域中
                req.setAttribute("msg", "用户名已存在！");
                req.setAttribute("username", username);
                req.setAttribute("email", email);


                req.getRequestDispatcher("/pages/user/regist.jsp").forward(req, resp);
            } else {
                //可用，调用service保存到数据库
                userService.registUser(new User(null, username, password, email));
                //跳到注册成功界面
                req.getRequestDispatcher("/pages/user/regist_success.jsp").forward(req, resp);
            }

        } else {
            //把回显信息保存到request域中
            req.setAttribute("msg", "验证码错误！");
            req.setAttribute("username", username);
            req.setAttribute("email", email);

            //不正确，跳回注册页面
            System.out.println("验证码[" + code + "]错误");
            req.getRequestDispatcher("/pages/user/regist.jsp").forward(req, resp);
        }
    }

    /**
     * 注销
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        // 1 销毁session中用户登录的信息（或者销毁session）
        req.getSession().invalidate();
        // 2 重定向到首页（或者登录界面）
        resp.sendRedirect(req.getContextPath());
    }


    protected void ajaxExistsUsername(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{

        //获取请求的参数username
        String username = req.getParameter("username");
        //调用userService.existsUsername()
        boolean existsUsername = userService.existsUsername(username);
        //把返回的结果封装成为map对象
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("existsUsername",existsUsername);
        Gson gson = new Gson();
        String json = gson.toJson(resultMap);
        resp.getWriter().write(json);
    }
}



