package tedu.sp01.service;


import tedu.sp01.pojo.Item;

import java.util.List;

public interface ItemService {
    List<Item> getItems(String orderId);//获取订单商品列表
    void decreaseNumbers(List<Item> list);//减少商品库存
}
