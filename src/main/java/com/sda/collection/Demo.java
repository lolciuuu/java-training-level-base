package com.sda.collection;

public class Demo {

    int x = getFoo();

    private int getFoo() {
        System.out.println("foo");
        return 0;
    }

    {
        System.out.println("blok");
    }

    static {
        System.out.println("static blok");
    }

    Demo() {
        System.out.println("konstruktor");
    }

    public static void main(String[] args) {
        new Demo();
    }


}
