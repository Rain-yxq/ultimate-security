package com.rain.security.common.exception;

/**
 * 自定义用户不存在异常
 * @author 左边
 * @date 2019/8/25 1:18 AM
 */
public class UserNotExistException extends RuntimeException {

    private String id;

    public UserNotExistException(String id) {
        super("该用户不存在");
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
