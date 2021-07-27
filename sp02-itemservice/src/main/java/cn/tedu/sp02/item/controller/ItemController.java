package cn.tedu.sp02.item.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tedu.sp01.pojo.Item;
import tedu.sp01.service.ItemService;
import tedu.web.util.JsonResult;

import java.util.List;
import java.util.Random;

@RestController
@Slf4j
public class ItemController {
    @Autowired
    private ItemService itemService;
    //获取商品列表
    @GetMapping("/{orderID}")
    public JsonResult<List<Item>> getItems(@PathVariable String orderID) throws InterruptedException {
        /*
        * JsonResult -响应结构的封装对象
        * -code 响应状态码
        * -msg 提示消息
        *-data 响应数据对象
        * */
        List<Item> items = itemService.getItems(orderID);

        if(Math.random()<0.9) {
            //90%概率延迟代码
            int t = new Random().nextInt(5000);//随机延迟0-5s
            System.out.println("延迟时间："+t);
            Thread.sleep(t);
        }

        return JsonResult.ok().data(items);
    }

    //减少商品库存
    /*客户端发送的请求协议，商品集合要包含在请求协议体中*/
    @PostMapping("/decreaseNumber")
    public JsonResult<?> decreaseNumbers(@RequestBody List<Item> items){
        /*@RequestBody 完整的接收协议体数据*/
        itemService.decreaseNumbers(items);
        return JsonResult.ok().msg("减少商品库存成功！");
    }

}
