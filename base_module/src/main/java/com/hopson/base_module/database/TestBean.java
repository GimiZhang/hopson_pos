package com.hopson.base_module.database;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class TestBean {
    @Id(autoincrement = true)
    private Long id;
    private String baseUrl;
    private String requestUrl;

    @Generated(hash = 1718761079)
    public TestBean(Long id, String baseUrl, String requestUrl) {
        this.id = id;
        this.baseUrl = baseUrl;
        this.requestUrl = requestUrl;
    }

    @Generated(hash = 2087637710)
    public TestBean() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String getRequestUrl() {
        return requestUrl;
    }

    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }
}
