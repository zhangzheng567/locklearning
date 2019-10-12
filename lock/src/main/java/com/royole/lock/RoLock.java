package com.royole.lock;

/**
 * @author : anzh
 * @date : 2019/9/25
 * @description :
 */
public class RoLock {
    volatile boolean mIsLock = false;
    public synchronized void lockCurrentObject() {
        System.out.println("lockCurrentObject");
    }

    public void lockAObeject() {
        synchronized (this) {
            System.out.println("lockAObeject");
        }
    }

    public static synchronized void lockCurrentClassObeject() {
        System.out.println("lockCurrentClassObeject");
    }
}
