package com.example;

import java.lang.ref.PhantomReference;
import java.lang.ref.SoftReference;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class MyClass {
    public static void main(String[] args) {

        other a = new other();

        System.out.println("a is new ok ");
        other b = new other(100);
        System.out.println("b is new ok ");

    }


}
