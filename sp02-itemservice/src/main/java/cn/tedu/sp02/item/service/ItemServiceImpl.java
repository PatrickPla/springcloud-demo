package cn.tedu.sp02.item.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tedu.sp01.pojo.Item;
import tedu.sp01.service.ItemService;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class ItemServiceImpl implements ItemService {

    @Override
    public List<Item> getItems(String orderId) {
        log.info("获取商品，orderID="+orderId);

        ArrayList<Item> items = new ArrayList<>();
        items.add(new Item(1,"商品1",1));
        items.add(new Item(2,"商品2",4));
        items.add(new Item(3,"商品3",2));
        items.add(new Item(4,"商品4",5));
        items.add(new Item(5,"商品5",3));
        return items;
    }

    @Override
    public void decreaseNumbers(List<Item> list) {
        log.info("减少商品库存");
        for (Item item : list) {
            log.info("商品:"+item);
        }

    }
}
