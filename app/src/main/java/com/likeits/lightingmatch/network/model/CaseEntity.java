package com.likeits.lightingmatch.network.model;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/1/18.
 */

public class CaseEntity implements Serializable {
    String url;
    String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
