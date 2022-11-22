package atguigu.dao;/**
 * @author DingDi
 * @create 2022-09-20 18:47
 */

import atguigu.pojo.Order;

/**
 *
 * @author: dingdi
 * @date: 2022/9/20 18:47
 */
public interface OrderDao {
    public int saveOrder(Order order);
}
