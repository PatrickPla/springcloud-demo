package cn.tedu.sp03.user.service;

import com.fasterxml.jackson.core.type.TypeReference;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import tedu.sp01.pojo.User;
import tedu.sp01.service.UserService;
import tedu.web.util.JsonUtil;

import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Value("${sp.user-service.users}")
    private String userJson;

    @Override
    public User getUser(Integer id) {
        log.info("users json string :"+userJson);
        //userJson转换成List<User> TypeReference jackJson提供的api
        List<User> list = JsonUtil.from(userJson, new TypeReference<List<User>>() {
        });
        for (User user : list) {
            if(user.getId().equals(id)){
                return user;
            }
        }
        //未找到用户，返回错误数据
        return new User(id,"用户名："+id,"密码："+id);
    }

    @Override
    public void addScore(Integer id, Integer score) {
        //增加积分
        log.info("用户："+id+" -增加积分 "+score);
    }
}
