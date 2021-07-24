package cn.tedu.sp04.order.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tedu.sp01.pojo.Order;
import tedu.sp01.service.OrderService;
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {


    //获取订单
    @Override
    public Order getOrder(String orderId) {
        log.info(" orderId："+orderId);
        //远程调用商品，获取商品列表

        //远程调用用户，获取用户信息

        Order order = new Order();
        order.setId(orderId);
        return order;
    }

    //添加订单
    @Override
    public void addOrder(Order order) {
        //远程调用减少商品库存
        //远程调用增加用户积分

        log.info("保存订单："+order);
    }
}
