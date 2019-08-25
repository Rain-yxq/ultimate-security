package com.rain.security.entity.dto;

import io.swagger.annotations.ApiModelProperty;

/**
 * 用户的查询条件
 * @author 左边
 * @date 2019/8/24 1:18 AM
 */
public class UserCondition {

    private String userName;
    @ApiModelProperty("年龄")
    private Integer age;
    private Integer ageTo;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getAgeTo() {
        return ageTo;
    }

    public void setAgeTo(Integer ageTo) {
        this.ageTo = ageTo;
    }

    @Override
    public String toString() {
        return "UserCondition{" +
                "userName='" + userName + '\'' +
                ", age=" + age +
                ", ageTo=" + ageTo +
                '}';
    }
}
