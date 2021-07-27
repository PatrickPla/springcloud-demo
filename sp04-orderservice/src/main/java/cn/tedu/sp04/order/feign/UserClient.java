package cn.tedu.sp04.order.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import tedu.sp01.pojo.User;
import tedu.web.util.JsonResult;

@FeignClient(name = "user-service")
public interface UserClient {
    //获取用户
    @GetMapping("/{userId}")
    JsonResult<User> getUser(@PathVariable Integer userId);

    //增加积分
    @GetMapping("/{userId}/score")
    JsonResult<?> addScore(@PathVariable Integer userId,
                           @RequestParam("score") Integer score);//@RequestParam 在controller中可以省略

}
