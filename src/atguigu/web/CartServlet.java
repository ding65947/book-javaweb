package atguigu.web;/**
 * @author DingDi
 * @create 2022-09-15 17:57
 */

import atguigu.pojo.Book;
import atguigu.pojo.Cart;
import atguigu.pojo.CartItem;
import atguigu.service.BookService;
import atguigu.service.impl.BookServiceImpl;
import atguigu.utils.WebUtils;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author: dingdi
 * @date: 2022/9/15 17:57
 */
public class CartServlet extends BaseSevlet{
    private BookService bookService= new BookServiceImpl();
    /**
     * 加入购物车
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void addItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求的参数 商品编号
        int id= WebUtils.parseInt(req.getParameter("id"),0);
        //调用bookService.queryBookById(id);Book得到图书信息
        Book book = bookService.queryBookById(id);
        //把图书信息，转换成为CartItem商品项
        CartItem cartItem = new CartItem(book.getId(), book.getName(), 1, book.getPrice(), book.getPrice());
        //调用Cart.addItem(CartItem);添加商品
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if(cart==null){
            cart=new Cart();
            req.getSession().setAttribute("cart",cart);
        }


        cart.addItem(cartItem);
        System.out.println(cart);

        //重定向回商品列表页面
        resp.sendRedirect(req.getHeader("Referer"));

        //最后一个添加商品的名称
        req.getSession().setAttribute("lastName",cartItem.getName());

    }

    /**
     * 删除购物车商品
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void deleteItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取商品编号
        int id=WebUtils.parseInt(req.getParameter("id"),0);
        //获取购物车对象
        Cart cart =(Cart) req.getSession().getAttribute("cart");

        if(cart!=null){
            //删除购物车商品项
            cart.deleteItem(id);
            //重定向回购物车展示页面
            resp.sendRedirect(req.getHeader("Referer"));
        }
    }

    /**
     * 清空购物车
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void clear(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取购物车对象
        Cart cart =(Cart) req.getSession().getAttribute("cart");
        if(cart!=null){
            //清空购物车
            cart.clear();
        }
        //重定向回购物车展示页面
        resp.sendRedirect(req.getHeader("Referer"));

    }


    /**
     * 修改商品数量
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void updateCount(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求参数 商品编号，商品数量
        int id = WebUtils.parseInt(req.getParameter("id"),0);
        int count=WebUtils.parseInt(req.getParameter("count"),1);
        //获取购物车对象
        Cart cart =(Cart) req.getSession().getAttribute("cart");

        if(cart!=null){
            cart.updateCount(id,count);
        }
        //重定向回购物车展示页面
        resp.sendRedirect(req.getHeader("Referer"));

    }



    protected void ajaxAddItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //获取请求的参数 商品编号
        int id= WebUtils.parseInt(req.getParameter("id"),0);
        //调用bookService.queryBookById(id);Book得到图书信息
        Book book = bookService.queryBookById(id);
        //把图书信息，转换成为CartItem商品项
        CartItem cartItem = new CartItem(book.getId(), book.getName(), 1, book.getPrice(), book.getPrice());
        //调用Cart.addItem(CartItem);添加商品
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if(cart==null){
            cart=new Cart();
            req.getSession().setAttribute("cart",cart);
        }


        cart.addItem(cartItem);

        //最后一个添加商品的名称
        req.getSession().setAttribute("lastName",cartItem.getName());


        //返回购物车总的商品数量和最后一个添加的商品名称
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("totalCount",cart.getTotalCount());
        resultMap.put("lastName",cartItem.getName());
        Gson gson = new Gson();
        String json = gson.toJson(resultMap);
        resp.getWriter().write(json);

    }
}
