package com.royole.locklearning.lock;

import java.util.concurrent.locks.LockSupport;

/**
 * @author : anzh
 * @date : 2019/10/9
 * @description :
 */
public class ThreadLife {
    public static void main(String[] args) {
        ThreadLife threadLife = new ThreadLife();
//        threadLife.useLockSupport();
//        threadLife.useObjectLock();
        threadLife.threadException();
    }

    /**
     * 在Java多线程中，当需要阻塞或者唤醒一个线程时，都会使用LockSupport工具类来完成相应的工作
     */
    private void useLockSupport() {
        final Thread thread0 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("before park thread0");
                LockSupport.park();
                System.out.println("after unpark thread0");
            }
        });
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("before sleep thread1");
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                LockSupport.unpark(thread0);
                System.out.println("after wake thread1");
            }
        });
        thread0.start();
        thread1.start();
    }

    /**
     * @throws IllegalMonitorStateException if the current thread is not the owner of the object's monitor.
     */
    private void useObjectLock() {
        final Object object0 = new Object();
        Thread thread0 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("before wait thread0");
                try {
                    synchronized (object0) {
                        object0.wait();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("after notify thread0");
            }
        });
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("before wait thread1");
                try {
                    synchronized (object0) {
                        object0.wait();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("after notify thread1");
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("before sleep thread2");
                    Thread.sleep(3000);
                    synchronized (object0) {
                        object0.notify();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println("after wake thread2");
            }
        });
        thread0.start();
        thread1.start();
        thread2.start();
    }

    private void threadException() {
        final Thread thread0 = new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    Thread.sleep(2000);
                    System.out.println("thread0 sleep ");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    System.out.println("thread0 sleep exception :" + e.getMessage());
                }
            }
        });
        thread0.start();
        thread0.interrupt();
        Thread.interrupted();
        System.out.println("thread0 interrupt ");
    }
}