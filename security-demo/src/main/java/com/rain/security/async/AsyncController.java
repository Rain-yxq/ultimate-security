package com.rain.security.async;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Callable;

/**
 * @author 左边
 * @date 2019/8/25 5:33 PM
 */
@RestController
public class AsyncController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @RequestMapping("/order")
    public Callable<String> order() {
        logger.info("主线程开始...");
        Callable<String> result = new Callable<String>() {
            @Override
            public String call() throws Exception {
                logger.info("副线程开始...");
                Thread.sleep(1000); // 模拟下单的处理
                logger.info("副线程返回...");
                return "success";
            }
        };
        logger.info("主线程完成！！！");
        return result;
    }
}
