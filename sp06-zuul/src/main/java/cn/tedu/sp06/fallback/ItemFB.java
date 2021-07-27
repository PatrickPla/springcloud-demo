package cn.tedu.sp06.fallback;

import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import tedu.web.util.JsonResult;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

/**
 * 调用商品服务失败，返回降级结果
 * Hystrix
 * */
@Component
public class ItemFB implements FallbackProvider {
    //针对哪个后台服务调用失败应用当前降级类
    //返回值： “item-service”  只针对商品服务降级 ； “*” 对所用后台服务都应用降级服务
    @Override
    public String getRoute() {
        return "item-service";
    }
    //返回给客户端的降级数据,封装到ClientHttpResponse对象中
    @Override
    public ClientHttpResponse fallbackResponse(String route, Throwable cause) {

        return new ClientHttpResponse() {
            @Override
            public HttpStatus getStatusCode() throws IOException {
                return HttpStatus.INTERNAL_SERVER_ERROR;
            }

            @Override
            public int getRawStatusCode() throws IOException {
                return HttpStatus.INTERNAL_SERVER_ERROR.value();
            }

            @Override
            public String getStatusText() throws IOException {
                return HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase();
            }

            @Override
            public void close() {

            }

            @Override
            public InputStream getBody() throws IOException {
                //JsonResult code 500 msg调用后台服务出错
                String Json = JsonResult.err().code(500).msg("调用后台服务出错").toString();
                //将json封装到bais,bais内存数组流不占用底层系统资源，不需要关闭
                return new ByteArrayInputStream(Json.getBytes(StandardCharsets.UTF_8));
            }

            @Override
            public HttpHeaders getHeaders() {
                //添加协议头属性 Context-Type: application/jso;charset=UTF-8
                HttpHeaders httpHeaders = new HttpHeaders();
                httpHeaders.set("Context-Type","application/jso;charset=UTF-8");
                return httpHeaders;
            }
        };
    }
}
