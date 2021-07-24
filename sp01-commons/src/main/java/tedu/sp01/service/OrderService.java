package tedu.sp01.service;


import tedu.sp01.pojo.Order;

public interface OrderService {
    Order getOrder(String orderId);//获取订单
    void addOrder(Order order);//添加订单
}


