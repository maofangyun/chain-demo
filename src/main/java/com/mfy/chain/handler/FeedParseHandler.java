package com.mfy.chain.handler;

import com.mfy.entity.FeedInfo;
import com.mfy.threadpool.SingleThreadPoolConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.ThreadPoolExecutor;

@Component
public class FeedParseHandler extends ParseHandler{

    private ThreadPoolExecutor executor = SingleThreadPoolConfig.singleExecutorService("FeedParse");

    private boolean flag;

    @Autowired
    public void setOrder() {
        super.setOrder(2);
    }

    @Override
    public <Feedinfo> void doHandle(Feedinfo feedinfo) {
        executor.execute(() -> {
            try {
                Thread.sleep(2000);
                String name = Thread.currentThread().getName();
                System.out.println(name+": 馈电协议------处理完成");
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
