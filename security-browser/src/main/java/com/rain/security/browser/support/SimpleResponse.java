package com.rain.security.browser.support;

/**
 * @author 左边
 * @date 2019/8/26 11:15 PM
 */
public class SimpleResponse {

    private Object content;

    public SimpleResponse(Object content) {
        this.content = content;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }
}
