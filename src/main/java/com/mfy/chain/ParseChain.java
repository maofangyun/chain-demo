package com.mfy.chain;

import com.mfy.chain.handler.ParseHandler;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 协议解析的执行链
 * */
@Component
public class ParseChain extends ExecutionChain<ParseHandler>{

    @Override
    public boolean support() {
        return true;
    }

    @PostConstruct
    @Override
    public void assembleChain(){
        list = new ArrayList<>();
        // 在ac中找出ParseHandler抽象类的所有实现类
        String[] names = applicationContext.getBeanNamesForType(ParseHandler.class);
        Arrays.stream(names).forEach(name -> {
            ParseHandler handler = applicationContext.getBean(name, ParseHandler.class);
            list.add(handler);
        });
        // 通过order字段进行排序
        list = this.list.stream().sorted((o1, o2) -> o1.getOrder()- o2.getOrder()).collect(Collectors.toList());
        // 完成链条的初始化
        for(int i = this.list.size()-1; i>=0; i--){
            ParseHandler curHandler = this.list.get(i);
            if(i-1 >= 0){
                ParseHandler preHandler = this.list.get(i-1);
                preHandler.setNext(curHandler);
            }
        }
        // 设置链条的头节点
        setHeader(list.get(0));
    }

    public boolean doChain(){

        return false;
    }

}
