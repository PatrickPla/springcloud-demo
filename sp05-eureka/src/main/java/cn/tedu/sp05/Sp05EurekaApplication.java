package cn.tedu.sp05;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
/**
 * 1.注册
 *      客户端重复尝试注册，直到注册成功为止
 * 2.拉取
 *      每30s拉取一次注册表，刷新注册表
 * 3.心跳
 *      客户端每30s发送一次心跳数据
 *      若服务器连续3次收不到心跳反馈会删除服务
 * 4.自我保护模式
 *      15min内，85%服务器出现心跳异常，自动进入保护模式
 *      所有注册信息不删除
 *      等待网络恢复正常后，会自动退出保护模式
 * */
@EnableEurekaServer//触发自动配置
@SpringBootApplication
public class Sp05EurekaApplication {

    public static void main(String[] args) {
        SpringApplication.run(Sp05EurekaApplication.class, args);
    }

}
