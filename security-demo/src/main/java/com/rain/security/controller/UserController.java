package com.rain.security.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.rain.security.common.exception.UserNotExistException;
import com.rain.security.entity.dto.User;
import com.rain.security.entity.dto.UserCondition;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 左边
 * @date 2019/8/24 12:59 AM
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {

    /**
     * RequestParam标注的参数必须在http请求中带上，不然会报错。
     * 如果其标注的参数是可选的，可以修改required属性为false
     * @param userName 用户名
     * @return 用户列表
     */
    @RequestMapping(method = RequestMethod.GET)
    @JsonView(User.UserSimpleView.class) // 在Controller方法上指定视图
    public List<User> query(@RequestParam(required = false) String userName) {
        System.out.println(userName);
        List<User> users = new ArrayList<>();
        users.add(new User());
        users.add(new User());
        users.add(new User());

//        throw new UserNotExistException("13");
        return users;
    }

    @RequestMapping(value = "/page", method = RequestMethod.GET)
    @JsonView(User.UserSimpleView.class) // 在Controller方法上指定视图
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

    /**
     * 获取用户信息
     * @param id @PathVariable注解可以接收请求路径中占位符的值，
     *           name属性中可以指定url路径上的参数名称绑定到注解的参数上
     * @return 用户
     */
    @GetMapping("/{idxxx}") // getMapping 相当于@RequestMapping+method = RequestMethod.GET
    @JsonView(User.UserDetailView.class) // 在Controller方法上指定视图
    public User getInfo(@PathVariable(value = "idxxx") String id) {
        System.out.println(id);
        User user = new User();
        user.setUserName("rain");
        return user;
    }

    /**
     * 创建用户
     * @param user 用户对象
     * @return 已创建的User对象
     */
    @PostMapping
    public User create(@Valid @RequestBody User user) {

        System.out.println(user.getId());
        System.out.println(user.getUserName());
        System.out.println(user.getPassword());
        System.out.println(user.getBirthday());

        user.setId("1");
        return user;
    }

    @PutMapping("/{id}")
    public User update(@Valid @RequestBody User user, BindingResult errors) {

        if (errors.hasErrors()) {
            errors.getAllErrors().forEach(
                    error -> System.out.println(error.getDefaultMessage()));

        }

        System.out.println(user.toString());

        System.out.println(user.getId());
        System.out.println(user.getUserName());
        System.out.println(user.getPassword());
        System.out.println(user.getBirthday());

        user.setId("1");
        return user;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        System.out.println(id);
    }
}
