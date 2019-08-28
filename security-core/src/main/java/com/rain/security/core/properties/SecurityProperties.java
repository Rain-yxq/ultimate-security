package com.rain.security.core.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author 左边
 * @date 2019/8/26 11:30 PM
 */
@ConfigurationProperties(prefix = "rain.security")
public class SecurityProperties {

    private BrowserProperties browser = new BrowserProperties();

    public BrowserProperties getBrowser() {
        return browser;
    }

    public void setBrowser(BrowserProperties browser) {
        this.browser = browser;
    }
}
