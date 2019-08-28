package com.rain.security.browser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @author 左边
 * @date 2019/8/26 4:43 PM
 */
@Component
public class MyUserDetailsServiceImpl implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(MyUserDetailsServiceImpl.class);

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("登陆用户名： " + username);
        // 根据用户名去数据库里查询信息，这里就不真查了，直接模拟查找到的密码
        String psw = "289443";

        String encode = passwordEncoder.encode(psw);
        logger.info("加密后的密码是：" + encode);

        // User对象是UserDetailsService的实现类，我们将数据库中该用户的信息返回，与页面中传入的密码进行对比来对用户进行认证。
        // 其中第三个参数是用户的权限列表
//        return new User(username, psw, AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
        return new User(username, encode, true, true, true,
                true,  AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));

    }
}
