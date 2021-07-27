package filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.commons.lang.StringUtils;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;
import tedu.web.util.JsonResult;

import javax.servlet.http.HttpServletRequest;


/*
* 检查用户登录
* */
@Component
public class LoginFilter extends ZuulFilter {
    /*过滤器类型 pre routing psot error*/
    @Override
    public String filterType() {
        //pre 前置过滤器类型
        return FilterConstants.PRE_TYPE;
    }
    /*过滤器的顺序号*/
    @Override
    public int filterOrder() {
        return 6;
    }
    /*判断当前请求是否执行过滤代码*/
    @Override
    public boolean shouldFilter() {
        //调用商品检查权限

        //调用用户的订单检查权限

        //判断当前请求调用是否是 item-service

        //获得一个请求上下文对象
        RequestContext currentContext = RequestContext.getCurrentContext();
        //获取服务器ID
        String serviceID = (String) currentContext.get(FilterConstants.SERVICE_ID_KEY);
        //判断服务器ID是否符合
        return serviceID.equals("item-service");
    }
    //过滤代码
    @Override
    public Object run() throws ZuulException {
        //获得上下文对象
        RequestContext currentContext = RequestContext.getCurrentContext();
        //获得request对象
        HttpServletRequest request = currentContext.getRequest();
        //接收token参数
        String token = request.getParameter("token");
        //如果无token阻止继续调用
        if(StringUtils.isBlank(token)) {
            currentContext.setSendZuulResponse(false);
            //返回JSONResult
            String Json = JsonResult.err().code(JsonResult.NOT_LOGIN).msg("未登录状态").toString();
            currentContext.addZuulResponseHeader("Context-Type", "application/json;charset=UTF-8");
            currentContext.setResponseBody(Json);
        }
        return null;
    }
}
