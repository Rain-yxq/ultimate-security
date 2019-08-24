package com.rain.security.entity.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.rain.security.common.validator.MyConstraint;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Past;
import java.util.Date;

/**
 * @author 左边
 * @date 2019/8/24 12:57 AM
 */
public class User {

    /** 使用接口来声明多个视图 */

    public interface UserSimpleView { }
    public interface UserDetailView extends UserSimpleView { }

    @MyConstraint(message = "用户id的自定义校验注解")
    private String id;
    private String userName;
    @NotBlank(message = "密码不能为空")
    private String password;
    @Past(message = "生日必须是过去的日期")
    private Date birthday;

    @JsonView(UserSimpleView.class)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @JsonView(UserSimpleView.class)
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @JsonView(UserDetailView.class)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @JsonView(UserSimpleView.class)
    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", birthday=" + birthday +
                '}';
    }
}
