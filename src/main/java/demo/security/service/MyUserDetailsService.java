package demo.security.service;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;

import demo.security.domain.LoginUser;
import demo.domain.Yonghu;
import demo.mapper.YonghuMapper;
import demo.service.IYonghuService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private YonghuMapper yonghuMapper;

    @Autowired
    private IYonghuService yonghuService;

    @Override
    public UserDetails loadUserByUsername(String yonghuming) throws UsernameNotFoundException {
        Yonghu yonghu = yonghuService.findByYonghuming(yonghuming);
        // 认证失败 交给自定义认证失败处理器处理
        if (Objects.isNull(yonghu)) {
            throw new RuntimeException("用户名或密码错误");
        }
        List<String> permission = yonghuService.getPermission(yonghu.getId());
        return new LoginUser(yonghu, permission);
    }
}
