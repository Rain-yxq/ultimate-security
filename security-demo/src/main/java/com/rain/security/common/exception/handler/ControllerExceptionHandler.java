package com.rain.security.common.exception.handler;

import com.rain.security.common.exception.UserNotExistException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

/**
 * controller全局异常处理器
 *
 * @author 左边
 * @date 2019/8/25 1:22 AM
 */
@ControllerAdvice // 可以理解成对于所有SpringMVC中controller的一个切面
public class ControllerExceptionHandler {

    @ExceptionHandler(UserNotExistException.class) // 指定要处理的异常
    @ResponseBody // 处理结果以json字符串返回
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) // 对于该异常要返回的http状态码
    public Map<String, Object> handleUserNotExistException(UserNotExistException ex) {
        Map<String, Object> result = new HashMap<>();
        result.put("id", ex.getId());
        result.put("message", ex.getMessage());
        return result;
    }
}
