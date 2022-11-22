package com.jpabasic.ex1hellojpa.hellojpa;

public class ValueMain {

    public static void main(String[] args) {
        //int와 double같은 기본타입(primitive type)은 자바가 sideEffect가 일어나지 않도록 복사처리를 해준다.
        int a = 10;
        int b = a;
        b = 20;
        System.out.println("a = " + a);
        System.out.println("b = " + b);


        Integer c = new Integer(10);
        Integer d = c;

        a.setValue(20);

        System.out.println("c = " + c);
        System.out.println("d = " + d);


    }
}
