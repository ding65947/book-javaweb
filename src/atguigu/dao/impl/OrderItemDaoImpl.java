package atguigu.dao.impl;/**
 * @author DingDi
 * @create 2022-09-20 18:55
 */

import atguigu.dao.OrderItemDao;
import atguigu.pojo.Order;
import atguigu.pojo.OrderItem;

/**
 *
 * @author: dingdi
 * @date: 2022/9/20 18:55
 */
public class OrderItemDaoImpl extends BaseDao implements OrderItemDao {
    @Override
    public int saveOrderItem(OrderItem orderItem) {
        String sql="insert into t_order_item(`name`,`count`,`price`,`total_price`,`order_id`) values(?,?,?,?,?)";
        return update(sql,orderItem.getName(),orderItem.getCount(),orderItem.getPrice(),orderItem.getTotalPrice(),orderItem.getOrderId());
    }
}
