package cn.webro.util;

import com.alibaba.fastjson.JSONArray;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.ActionContext;
import org.nutz.mvc.ActionFilter;
import org.nutz.mvc.View;
import org.nutz.mvc.view.VoidView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description:
 * @Param:
 * @return:
 * @Author: zhanghuixin
 * @Date: 2020/11/13
 */
@IocBean
public class TokenServlet implements ActionFilter {
    @Override
    public View match(ActionContext actionContext) {

        HttpServletRequest request = actionContext.getRequest();
        HttpServletResponse response = actionContext.getResponse();
        String token = request.getHeader("Authorization");

        System.out.println(token);
        boolean flg = false;
        if (token != null) {
            flg = TokenUtils.verify(token);
        }
        if (flg) {

            return null;

        } else {

            try {
                Map<String, Object> map = new HashMap<>();
                map.put("code", 401);
                String s = JSONArray.toJSONString(map);
                PrintWriter writer = response.getWriter();
                writer.println(s);
                writer.flush();
                writer.close();

            } catch (IOException e) {
                e.printStackTrace();
            }

            return new VoidView();
        }


    }


//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//
//    }
//
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//
//        boolean flg = false;
//        HttpServletRequest request = (HttpServletRequest) servletRequest;
//        HttpServletResponse response = (HttpServletResponse) servletResponse;
//        String token = request.getHeader("token");
//
//        System.out.println(token);
//
//        if (token != null) {
//            flg = TokenUtils.verify(token);
//        }
//        if (flg) {
//            filterChain.doFilter(request, response);
//        } else {
//
//            Map<String, Object> map = new HashMap<>();
//            map.put("code", 401);
//            String s = JSONArray.toJSONString(map);
//            PrintWriter writer = response.getWriter();
//            writer.println(s);
//            writer.flush();
//            writer.close();
//        }
//    }
//
//    @Override
//    public void destroy() {
//
//    }
}
