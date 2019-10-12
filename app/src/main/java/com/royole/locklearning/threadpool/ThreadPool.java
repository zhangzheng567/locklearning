package com.royole.locklearning.threadpool;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RunnableFuture;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author : anzh
 * @date : 2019/10/10
 * @description :
 */
public class ThreadPool {
    private int threadCounter = 0;
    private final ThreadFactory sThreadFactory = new ThreadFactory() {
        @Override
        public Thread newThread(Runnable r) {
            threadCounter++;
            return new Thread(r, "worker_thread_" + threadCounter);
        }
    };
    public static void main(String[] args){
        ThreadPool threadPool = new ThreadPool();
        threadPool.init();
    }

    /**
     * 1.创建线程池
     * 2.比较 execute() 和 submit() 的区别
     */
    private void init() {
        ThreadPoolExecutor workerPool = new ThreadPoolExecutor(getCorePoolSize(), getMaxPoolSize(), 60,
                TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(), sThreadFactory);

        workerPool.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("execute a task");
            }
        });

        workerPool.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println("submit a task");
            }
        });


        Future<Object> futureObject = workerPool.submit(new Callable<Object>() {
            @Override
            public Object call() throws Exception {
                System.out.println("submit a future task");
                try {
                    Thread.sleep(5000);
                    System.out.println("submit a future task after sleep");
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                return this;
            }
        });
        try {
            Thread.sleep(1000);
            futureObject.get();
            System.out.println("main thread go on ");
        }catch (InterruptedException e){

        }catch (ExecutionException e){

        }finally {
            workerPool.shutdown();
        }
    }

    protected int getCorePoolSize(){
        return Runtime.getRuntime().availableProcessors() * 2 + 1;
    }

    protected int getMaxPoolSize(){
        return 50;
    }


}
