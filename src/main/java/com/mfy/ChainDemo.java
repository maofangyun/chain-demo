package com.mfy;

import com.mfy.chain.ParseChain;
import com.mfy.entity.PdxpInfo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class ChainDemo {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(ChainDemo.class, args);
        ParseChain chain = context.getBean(ParseChain.class);
        if(chain.support()){
            chain.getHeader().doHandle(new PdxpInfo());
        }
        System.out.println("主流程完成");
    }
}
