package com.github.fashionbrot.console.interceptor;


import com.alibaba.fastjson.JSON;
import com.github.fashionbrot.common.enums.RespCode;
import com.github.fashionbrot.common.model.LoginModel;
import com.github.fashionbrot.common.util.CookieUtil;
import com.github.fashionbrot.common.vo.RespVo;
import com.github.fashionbrot.core.service.SysMenuService;
import com.github.fashionbrot.core.service.SysUserService;
import com.github.fashionbrot.core.service.UserLoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 后台系统拦截器
 */
@Slf4j
@Component
public class BackstageInterceptor implements HandlerInterceptor {


    @Autowired
    private UserLoginService userLoginService;

    @Autowired
    private SysMenuService sysMenuService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        if (log.isDebugEnabled()){
            log.debug("request url:{}",request.getRequestURI());
        }

        LoginModel model = userLoginService.getSafeLogin();
        if (model == null) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            if (!handlerMethod.hasMethodAnnotation(ResponseBody.class)){
                response.sendRedirect(url(request)+"/login");
            }else{
                response.setHeader("login","true");
                return false;
            }
            return true;
        }
        if (!sysMenuService.checkPermission(handler,model)){
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            if ( handlerMethod.hasMethodAnnotation(ResponseBody.class)){
                returnJson(response, RespVo.builder()
                        .code(RespVo.FAILED)
                        .msg(RespCode.NOT_PERMISSION_ERROR.getMsg())
                        .build());
                return false;
            }
            response.sendRedirect(url(request)+"/401?requestUrl="+request.getRequestURI());
            return false;
        }else{
            return true;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        CookieUtil.rewriteCookie(request,response,false);
    }

    public String url(HttpServletRequest request){
        String path = request.getContextPath();
        return request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
    }



    private void returnJson(HttpServletResponse response, Object json){
        PrintWriter writer = null;
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=utf-8");
        try {
            writer = response.getWriter();
            writer.print(JSON.toJSON(json));
        } catch (IOException e) {
            log.error("response error",e);
        } finally {
            if (writer != null)
                writer.close();
        }
    }

}
