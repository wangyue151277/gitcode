package com.itdr.common;

public class ResponseCode<T> {

    //状态码，错误信息，数据
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

    //成功之后，返回状态码(0)和数据
    public static <T> ResponseCode successRS(T data){
        ResponseCode rc = new ResponseCode();
        rc.setStats(0);
        rc.setData(data);
        return rc;
    }


    //失败之后，返回状态码和信息
    public static ResponseCode failtureRS(Integer stats, String mag){
        ResponseCode rc = new ResponseCode();
        rc.setStats(stats);
        rc.setMag(mag);
        return rc;
    }

    //toString方法
    @Override
    public String toString() {
        return "ResponseCode{" +
                "stats=" + stats +
                ", mag='" + mag + '\'' +
                ", data=" + data +
                '}';
    }

}

