package com.li.utils.filetransfer;

import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Create by Mingwei Li on 2016 2016-12-28 ����9:36:21
 */
public class CustomBlockingThreadPoolExecutor {

    private ThreadPoolExecutor pool = null;

    /**
     * 线程池初始化方法
     * <p>
     * corePoolSize 核心线程池大小----1 maximumPoolSize 最大线程池大小----3 keepAliveTime
     * 线程池中超过corePoolSize数目的空闲线程最大存活时间----30+单位TimeUnit TimeUnit
     * keepAliveTime时间单位----TimeUnit.MINUTES workQueue 阻塞队列----new
     * ArrayBlockingQueue<Runnable>(5)====5容量的阻塞队列 threadFactory 新建线程工厂----new
     * CustomThreadFactory()====定制的线程工厂 rejectedExecutionHandler
     * 当提交任务数超过maxmumPoolSize+workQueue之和时,
     * 即当提交第41个任务时(前面线程都没有执行完,此测试方法中用sleep(100)),
     * 任务会交给RejectedExecutionHandler来处理
     */
    public void init() {
        pool = new ThreadPoolExecutor(10, 10, 30, TimeUnit.MINUTES,
                new ArrayBlockingQueue<Runnable>(5), new CustomThreadFactory(),
                new CustomRejectedExecutionHandler());
    }

    public int getAliveThread() {
        return pool.getActiveCount();
    }

    public List<Runnable> getShutdownThread() {
        return pool.shutdownNow();
    }

    public void destory() {
        if (pool != null) {
            pool.shutdownNow();
        }
    }

    public ExecutorService getCustomThreadPoolExecutor() {
        return this.pool;
    }

    private class CustomThreadFactory implements ThreadFactory {

        private AtomicInteger count = new AtomicInteger(0);

        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(r);
            String threadName = CustomBlockingThreadPoolExecutor.class.getSimpleName()
                    + count.addAndGet(1);
            System.out.println(threadName);
            t.setName(threadName);
            System.out.println("333333333333333333333");

            return t;
        }
    }

    private class CustomRejectedExecutionHandler implements
            RejectedExecutionHandler {

        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            try {// 核心改造点，由blockingqueue的offer改成put阻塞方法
                executor.getQueue().put(r);
                System.out.println("111111111111111111111  "
                        + executor.getQueue().size());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("22222222222222222222");
        }
    }

}
