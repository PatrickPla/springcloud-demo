package cn.tedu.sp04.order.feign;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import tedu.sp01.pojo.Item;
import tedu.web.util.JsonResult;

import java.util.List;

//通过该接口，调用远程的商品服务
//通过该注解配置 1.调用哪个服务 2.调用服务的哪个路径 3.向这个路径提交什么参数
@FeignClient(name = "item-service")
public interface ItemClient {
    //获取商品列表
    @GetMapping("/{orderId}")
    JsonResult<List<Item>> getItems(@PathVariable String orderId);

    //减少商品库存
    @PostMapping("/decreaseNumber")
    JsonResult<?> decreaseNumber(@RequestBody List<Item> items);
}
