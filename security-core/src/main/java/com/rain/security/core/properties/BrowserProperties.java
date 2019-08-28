package com.rain.security.core.properties;

/**
 * @author 左边
 * @date 2019/8/26 11:32 PM
 */
public class BrowserProperties {

    /**
     * 设置默认的登录页，如果用户设置了自己的登录页则会覆盖这个默认值
     */
    private String loginPage = "/rain-signIn.html";

    public String getLoginPage() {
        return loginPage;
    }

    public void setLoginPage(String loginPage) {
        this.loginPage = loginPage;
    }
}
