package com.mfy.chain;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import java.util.List;

@Data
public abstract class ExecutionChain<T> {

    protected T header;

    protected List<T> list;

    @Autowired
    protected ApplicationContext applicationContext;

    /**
     * 判断是否支持此处理链
     * */
    public abstract boolean support();

    /**
     * 组装执行链
     * */
    public abstract void assembleChain();
}
