package com.dto;

/**
 * Created by shejiewei on 2019/3/26.
 */
public class SimpleResponse {
    private  Object content;

    public SimpleResponse(Object content){
        this.content=content;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }
}
