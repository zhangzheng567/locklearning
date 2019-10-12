package com.royole.lock;

/**
 * @author : anzh
 * @date : 2019/9/30
 * @description :
 */
public class MyClass {
    static int number = 0;
    static volatile boolean flag = true;
    public static void main(String[] args) throws InterruptedException{
        final int count = 10000;
        Thread thread0 = new Thread(new Runnable() {
            @Override
            public void run() {
               for (int i = 0; i < 10000;i++){
                   number++;
               }
            }
        });
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10000;i++){
                    number++;
                }
            }
        });
        thread0.start();
        thread1.start();
        thread0.join();
        thread1.join();
        System.out.println(" number = " + number);
    }

    public void testFlag() throws InterruptedException{
        Thread thread0 = new Thread(new Runnable() {
            @Override
            public void run() {
               while (!flag){
               }
                System.out.println("thread0 flag = true");
            }
        });
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
               flag = false;
                System.out.println("thread flag = false");
            }
        });
        thread0.start();
        thread1.start();
        thread1.join();
        System.out.println(" number = " + number);
    }


    volatile int x = 0;
    volatile int y = 0;

    public void test() throws InterruptedException {
        Thread thread0 = new Thread(new Runnable() {
            @Override
            public void run() {
                int a = x;
                y = 1;
                System.out.println("a = " + a);
            }
        });
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                int b = y;
                x = 1;
                System.out.println("b = " + b);
            }
        });
        thread0.start();
        thread1.start();
    }

}
