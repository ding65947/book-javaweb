package atguigu.web;/**
 * @author DingDi
 * @create 2022-09-21 14:36
 */

import atguigu.pojo.Cart;
import atguigu.pojo.User;
import atguigu.service.OrderService;
import atguigu.service.impl.OrderServiceImpl;
import atguigu.utils.JdbcUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 * @author: dingdi
 * @date: 2022/9/21 14:36
 */
public class OrderServlet extends BaseSevlet{
    private OrderService orderService = new OrderServiceImpl();
    /**
     * 生成订单
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void createOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //先获取Cart购物车对象
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        //获取UserId
        User loginUser =(User) req.getSession().getAttribute("user");
        if(loginUser==null){
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req,resp);
            return;
        }

        Integer userId = loginUser.getId();
        String orderId = orderId = orderService.createOrder(cart, userId);


        //将订单号保存到域
//        req.setAttribute("orderId",orderId);
//        //请求转发到/pages/cart/checkout.jsp
//        req.getRequestDispatcher("/pages/cart/checkout.jsp").forward(req,resp);
        //

        //为防止表单重复提交，将请求转发改为重定向
        //将订单号保存到session域
        req.getSession().setAttribute("orderId",orderId);
        //重定向
        resp.sendRedirect(req.getContextPath()+"/pages/cart/checkout.jsp");

    }
}
