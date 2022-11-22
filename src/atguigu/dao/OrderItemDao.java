package atguigu.dao;/**
 * @author DingDi
 * @create 2022-09-20 18:49
 */

import atguigu.pojo.Order;
import atguigu.pojo.OrderItem;

/**
 *
 * @author: dingdi
 * @date: 2022/9/20 18:49
 */
public interface OrderItemDao {
    public int saveOrderItem(OrderItem orderItem);
}
