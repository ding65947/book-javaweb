package atguigu.test;

import atguigu.dao.OrderItemDao;
import atguigu.dao.impl.OrderItemDaoImpl;
import atguigu.pojo.OrderItem;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * @author DingDi
 * @create 2022-09-20 19:12
 */
public class OrderItemDaoTest {

    @Test
    public void saveOrderItem() {
        OrderItemDao orderItemDao=new OrderItemDaoImpl();
        orderItemDao.saveOrderItem(new OrderItem(null,"java从入门到精通",1,new BigDecimal(100),new BigDecimal(100),"1234567890"));
        orderItemDao.saveOrderItem(new OrderItem(null,"python从入门到精通",2,new BigDecimal(100),new BigDecimal(200),"1234567890"));
        orderItemDao.saveOrderItem(new OrderItem(null,"c++从入门到精通",1,new BigDecimal(100),new BigDecimal(100),"1234567890"));
    }
}