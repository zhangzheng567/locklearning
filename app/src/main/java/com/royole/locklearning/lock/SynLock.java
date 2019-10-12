package com.royole.locklearning.lock;

import java.util.concurrent.BlockingQueue;

/**
 * @author : anzh
 * @date : 2019/9/25
 * @description :
 */
public class SynLock {
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
