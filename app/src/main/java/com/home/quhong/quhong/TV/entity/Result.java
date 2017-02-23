package com.home.quhong.quhong.TV.entity;

/**
 * Imitation by Abybxc on 16/8/22 20:48
 * weixin:aserbao
 * <p/>
 * 直播数据模型基础类
 */
public class Result<T>
{

    public int code;

    public String message;

    public T result;

    public T data;

    public T getResult()
    {
        return result;
    }

    public T getData()
    {

        return result;
    }
}
