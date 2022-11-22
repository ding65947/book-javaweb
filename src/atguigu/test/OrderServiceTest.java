package atguigu.test;

import atguigu.pojo.Cart;
import atguigu.pojo.CartItem;
import atguigu.service.OrderService;
import atguigu.service.impl.OrderServiceImpl;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * @author DingDi
 * @create 2022-09-21 14:30
 */
public class OrderServiceTest {

    @Test
    public void createOrder() {

        Cart cart = new Cart();

        cart.addItem(new CartItem(1,"java从入门到精通",1,new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItem(new CartItem(1,"java从入门到精通",1,new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItem(new CartItem(2,"数据结构与算法",1,new BigDecimal(100),new BigDecimal(100)));

        OrderService orderService=new OrderServiceImpl();

        System.out.println("订单号是："+orderService.createOrder(cart,1));

    }
}