package com.itdr.common;

public class ResponseCode<T> {

    private Integer stats;
    private String mag;
    private T data;

    public Integer getStats() {
        return stats;
    }

    public void setStats(Integer stats) {
        this.stats = stats;
    }

    public String getMag() {
        return mag;
    }

    public void setMag(String mag) {
        this.mag = mag;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResponseCode{" +
                "stats=" + stats +
                ", mag='" + mag + '\'' +
                ", data=" + data +
                '}';
    }
/*
    成功返回状态码和数据
    失败返回状态码和失败信息
     */
}

