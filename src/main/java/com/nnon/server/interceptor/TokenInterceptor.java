package com.nnon.server.interceptor;

import com.alibaba.fastjson.JSON;
import com.nnon.server.comman.CommonResult;
import com.nnon.server.utils.TokenUtil;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Component
public class TokenInterceptor implements HandlerInterceptor {
    @Autowired
    TokenUtil utils;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,Object handler)throws Exception{
        if(request.getMethod().equals("OPTIONS")){
            response.setStatus(HttpServletResponse.SC_OK);
            return true;
        }
        System.out.println( request.getRequestURL());
        response.setCharacterEncoding("utf-8");

        String token = request.getHeader("Authorization");
        System.out.println(token);
        if(token != null){
            String result = utils.verify(token,response);
            if(!result.isEmpty()){
                request.setAttribute("verifyUid",result);
                System.out.println("通过拦截器");
                return true;
            }
        }

        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter out = null;
        try{
            CommonResult<String> unauthorized = CommonResult.unauthorized("认证失败，未通过拦截器");
            response.getWriter().append(JSON.toJSONString(unauthorized));
            System.out.println("认证失败，未通过拦截器");
            //        response.getWriter().write("50000");
        }catch (Exception e){
            e.printStackTrace();
            response.sendError(500);
            return false;
        }
        return false;

    }
}

