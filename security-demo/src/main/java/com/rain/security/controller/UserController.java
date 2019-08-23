package com.rain.security.controller;

import com.rain.security.entity.dto.User;
import com.rain.security.entity.dto.UserCondition;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 左边
 * @date 2019/8/24 12:59 AM
 */
@RestController
public class UserController {

    /**
     * RequestParam标注的参数必须在http请求中带上，不然会报错。
     * 如果其标注的参数是可选的，可以修改required属性为false
     * @param userName 用户名
     * @return 用户列表
     */
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public List<User> query(@RequestParam(required = false) String userName) {
        System.out.println(userName);
        List<User> users = new ArrayList<>();
        users.add(new User());
        users.add(new User());
        users.add(new User());
        return users;
    }

    @RequestMapping(value = "/page/user", method = RequestMethod.GET)
    public List<User> queryWithPage(UserCondition userCondition, @PageableDefault(page = 1, size = 15, sort = "userName, asc") Pageable pageable) {
        System.out.println(userCondition.toString());
        System.out.println("==============================================");
        System.out.println(pageable.getPageNumber());
        System.out.println(pageable.getPageSize());
        System.out.println(pageable.getSort());

        List<User> users = new ArrayList<>();
        users.add(new User());
        users.add(new User());
        users.add(new User());
        return users;
    }




}
