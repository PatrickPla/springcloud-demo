package cn.tedu.sp04.order.service;

import cn.tedu.sp04.order.feign.ItemClient;
import cn.tedu.sp04.order.feign.UserClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tedu.sp01.pojo.Item;
import tedu.sp01.pojo.Order;
import tedu.sp01.pojo.User;
import tedu.sp01.service.OrderService;
import tedu.web.util.JsonResult;

import java.util.List;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
    @Autowired
    private ItemClient itemClient;
    @Autowired
    private UserClient userClient;


    //获取订单
    @Override
    public Order getOrder(String orderId) {
        log.info(" orderId："+orderId);

        //远程调用商品，获取商品列表
        JsonResult<List<Item>> items = itemClient.getItems(orderId);
        //远程调用用户，获取用户信息
        JsonResult<User> user = userClient.getUser(8);

        Order order = new Order();
        order.setId(orderId);
        order.setUser(user.getData());
        order.setItems(items.getData());
        return order;
    }

    //添加订单
    @Override
    public void addOrder(Order order) {
        //远程调用减少商品库存
        itemClient.decreaseNumber(order.getItems());

        //远程调用增加用户积分
        userClient.addScore(order.getUser().getId(),1000);

        log.info("保存订单："+order);
    }
}
