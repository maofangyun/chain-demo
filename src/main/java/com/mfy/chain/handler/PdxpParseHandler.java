package com.mfy.chain.handler;

import com.mfy.entity.FeedInfo;
import com.mfy.threadpool.SingleThreadPoolConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.ThreadPoolExecutor;

@Component
public class PdxpParseHandler extends ParseHandler{

    private ThreadPoolExecutor executor = SingleThreadPoolConfig.singleExecutorService("PdxpParse");

    private boolean flag;

    @Autowired
    public void setOrder() {
        super.setOrder(1);
    }

    @Override
    public <PdxpInfo> void doHandle(PdxpInfo pdxpInfo) {
        executor.execute(() -> {
            try {
                Thread.sleep(2000);
                String name = Thread.currentThread().getName();
                System.out.println(name+": pdxp协议------处理完成");
                flag =true;
            } catch (InterruptedException e) {
                e.printStackTrace();
                flag = false;
            }
            if(flag){
                ParseHandler next = getNext();
                if(next != null){
                    next.doHandle(new FeedInfo());
                }
            }
        });
    }
}
