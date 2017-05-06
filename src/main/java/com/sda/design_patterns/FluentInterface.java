package com.sda.design_patterns;

public class FluentInterface {

    static class FluentClass {

        public FluentClass method1() {
            System.out.println("method1");
            return this;
        }

        public FluentClass method2() {
            System.out.println("method2");
            return this;
        }

        public FluentClass method3() {
            System.out.println("method3");
            return this;
        }

        public String getResult() {
            return "result";
        }
    }

    public static void main(String[] args) {
        FluentClass fluentClass = new FluentClass();

        String result = fluentClass
                .method1()
                .method2()
                .method3()
                .getResult();

        System.out.println(result);
    }
}
