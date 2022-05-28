package demo.security.filter;


import demo.security.domain.LoginUser;
import demo.domain.Yonghu;
import demo.service.ITokenService;
import demo.service.IYonghuService;
import demo.util.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
    @Autowired
    private IYonghuService yonghuService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 获取token
        String token = request.getHeader("token");
        if (!StringUtils.hasText(token)) {
            // 放行 后续过滤器再判断
            filterChain.doFilter(request,response);
            return;
        }


        // 解析token
        String yonghuming;
        try {
            Claims claims = JwtUtil.parseToken(token);
            yonghuming = (String) claims.get("yonghuming");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("token非法"); //-->跳转到未认证处理器
        }

        Yonghu yonghu = yonghuService.findByYonghuming(yonghuming);

        //查询状态
        if (yonghu.getState()==0){
            throw new RuntimeException("用户未登录"); //-->跳转到未认证处理器
        }

        //权限获取
        List<String> permission = yonghuService.getPermission(yonghu.getId());
        LoginUser loginUser = new LoginUser(yonghu, permission);
        System.out.println(loginUser);
        //存入SecurityContextHolder
        //获取权限信息封装到Authentication中
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginUser,null,loginUser.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        // 放行
        filterChain.doFilter(request, response);


    }
}
