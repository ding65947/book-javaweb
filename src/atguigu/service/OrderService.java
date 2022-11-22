package atguigu.service;/**
 * @author DingDi
 * @create 2022-09-21 14:13
 */

import atguigu.pojo.Cart;

/**
 *
 * @author: dingdi
 * @date: 2022/9/21 14:13
 */
public interface OrderService {
    public String createOrder(Cart cart,Integer userId);
}
