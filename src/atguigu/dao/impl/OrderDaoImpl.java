package atguigu.dao.impl;/**
 * @author DingDi
 * @create 2022-09-20 18:50
 */

import atguigu.dao.OrderDao;
import atguigu.pojo.Order;

/**
 *
 * @author: dingdi
 * @date: 2022/9/20 18:50
 */
public class OrderDaoImpl extends BaseDao implements OrderDao {
    @Override
    public int saveOrder(Order order) {
        String sql="insert into t_order(`order_id`,`create_time`,`price`,`status`,`user_id`) values(?,?,?,?,?)";

        return update(sql,order.getOrderId(),order.getCreateTime(),order.getPrice(),order.getStatus(),order.getUserId());
    }
}
