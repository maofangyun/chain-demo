package com.mfy.chain.handler;

import lombok.Data;

/**
 * 协议解析处理器抽象类
 * */
@Data
public abstract class ParseHandler{

    /**
     * 用于处理器排序
     * */
    private int order;

    /**
     * 后置节点
     * */
    private ParseHandler next;

    /**
     * 具体处理逻辑待子类实现
     */
    public abstract  <T> void doHandle(T t);

}
