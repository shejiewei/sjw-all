package com.core;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by shejiewei on 2019/3/26.
 */

@ConfigurationProperties(prefix = "imooc.security")
public class SecurityProperties {

    private BrowserProperties browser = new BrowserProperties();

    public BrowserProperties getBrowser() {
        return browser;
    }

    public void setBrowser(BrowserProperties browser) {
        this.browser = browser;
    }
}
