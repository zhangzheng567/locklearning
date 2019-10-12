package com.royole.locklearning.lock;

/**
 * @author : anzh
 * @date : 2019/10/8
 * @description :
 */
public class DeadLock {
    public final static Object object0 = new Object();
    public final static Object object1 = new Object();

    public static void main(String[] args) {
        DeadLock deadLock = new DeadLock();
        deadLock.deadLock();

    }

    private void deadLock() {

        Thread thread0 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (object0) {
                    System.out.println("lock object0");
                    try {
                        Thread.sleep(10000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    synchronized (object1) {
                        System.out.println("lock object1");
                    }
                }
            }
        });
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (object1) {
                    System.out.println("lock object1");
                    try {
                        Thread.sleep(10000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    synchronized (object0) {
                        System.out.println("lock object1");
                    }
                }
            }
        });

        thread0.start();
        thread1.start();
    }
}
