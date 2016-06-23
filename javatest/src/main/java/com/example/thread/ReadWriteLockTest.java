package com.example.thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by zhaoningqiang on 16/6/19.
 */

public class ReadWriteLockTest {
    public static void main(String[] args){
        ReadWriteLock lock = new ReentrantReadWriteLock();
    }
}
