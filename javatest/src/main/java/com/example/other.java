package com.example;

/**
 * Created by zhaoningqiang on 16/7/12.
 */

public class other {
    int index;
    {
        System.out.println("vvvvvvv");
    }

    {
        System.out.println("no name construct method index = "+index);
    }
    {
        System.out.println("wwwww");
    }


    public other(){
        System.out.println("other ----  null ");
    }



    public other(int index){
        this.index = index;
        System.out.println("other ----  index =  "+index);
    }
    {
        System.out.println("ooooooooooooooooo");
    }
}
