package com.lyq.common.interceptor;

import com.lyq.common.exception.MyExceptionInfo;
import com.lyq.system.entity.sys_user.vo.LoginUserInfo;
import com.lyq.common.utils.IPUtil;
import com.lyq.common.utils.LoginUserUtils;
import com.google.gson.Gson;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @email 2434017367@qq.com
 * @author: zhy
 * @date: 2021/1/29
 * @time: 9:59
 */
@Log4j2
public class ReqLogInterceptor implements HandlerInterceptor {

    private static final Gson gson = new Gson();

    private static final ThreadLocal<Long> threadLocal = new ThreadLocal<Long>();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 保存请求开始时的时间毫秒值
        long timeMillis = System.currentTimeMillis();
        threadLocal.set(timeMillis);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

        if ("OPTIONS".equals(request.getMethod())){
            return;
        }

        try {
            long startTime = threadLocal.get();
            threadLocal.remove();

            long endTime = System.currentTimeMillis();
            // 获取请求url
            String requestURI = request.getRequestURI();
            // 获取请求方法
            String method = request.getMethod();
            // 获取请求ip
            String ip = IPUtil.getIpAddr(request);
            // 获取请求用户
            LoginUserInfo loginUser = LoginUserUtils.getLoginUserInfo();
            String username = null;
            if (loginUser != null){
                username = loginUser.getUsername();
            }
            // 获取请求参数
            Map<String, String[]> parameterMap = request.getParameterMap();
            String reqPar = gson.toJson(parameterMap);
            // 计算请求执行耗时
            long duration = endTime - startTime;

            // 日志封装
            String logs = String.format("用户：%s，uri：%s，method：%s，ip：%s，执行耗时：%dms，请求参数：%s", username, requestURI, method, ip, duration, reqPar);

            // 查看是否有异常信息
            MyExceptionInfo myExceptionInfo = MyExceptionInfo.getExceptionInfo();
            if (myExceptionInfo != null){
                logs = "errCode：" + myExceptionInfo.getCode() + "，errMsg：" + myExceptionInfo.getMsg() + "，" + logs;
                log.error(logs, myExceptionInfo.getE());
            }else{
                log.info(logs);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
