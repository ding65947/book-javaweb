package atguigu.test;

import atguigu.dao.OrderDao;
import atguigu.dao.impl.OrderDaoImpl;
import atguigu.pojo.Order;
import org.junit.Test;

import javax.xml.crypto.Data;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * @author DingDi
 * @create 2022-09-20 19:07
 */
public class OrderDaoTest {

    @Test
    public void saveOrder() {
        OrderDao orderDao=new OrderDaoImpl();
        orderDao.saveOrder(new Order("1234567890",new Date(),new BigDecimal(100),0,1));
    }
}