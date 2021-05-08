package com.mfy.threadpool;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class SingleThreadPoolConfig {

    public static ThreadPoolExecutor singleExecutorService(String name){
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(1,
                1,
                0,
                TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<Runnable>(100),
                // 线程池中线程的名称
                new NamedThreadFactory(name),
                // 拒绝策略,调用主线程执行任务
                new ThreadPoolExecutor.CallerRunsPolicy());
        return poolExecutor;
    }

    static class NamedThreadFactory implements ThreadFactory {
        private static final AtomicInteger poolNumber = new AtomicInteger(1);
        private final AtomicInteger threadNumber = new AtomicInteger(1);
        private  String namePrefix;
        private final ThreadGroup group;

        public NamedThreadFactory( String name ) {
            this.namePrefix = name + "-" + poolNumber.getAndIncrement() + "-thread-";
            SecurityManager s = System.getSecurityManager();
            group = (s != null) ? s.getThreadGroup() : Thread.currentThread().getThreadGroup();
        }

        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(group, r, namePrefix + threadNumber.getAndIncrement(), 0);
            if (t.isDaemon()) {
                t.setDaemon(false);
            }
            if (t.getPriority() != Thread.NORM_PRIORITY) {
                t.setPriority(Thread.NORM_PRIORITY);
            }
            return t;
        }
    }
}
