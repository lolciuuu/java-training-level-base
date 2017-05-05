package com.sda.collection;

public class Launcher {

    static class RecursionExample {

        public long fibonnaci1(long start, long end) { //784139401
            if (end >= 1) {
                return start + fibonnaci1(++start, --end);
            }

            return 0;
        }

        public long fibonnaci2(long start, long end, long sum) { //1764328503 1764090901
            if (end > 1) {
                return fibonnaci2(++start, --end, sum + start);
            }

            return sum;
        }

    }

    public static void main(String... args) {
        long result2 = 0;
        long endNumber = 1;
        try {

            while (true) {
                result2 = new RecursionExample().fibonnaci2(1, endNumber, 0l);
                endNumber++;
            }
        }
        catch (StackOverflowError e) {
        }

        System.out.println("Fibo 2 result:" + result2);


        long result1 = 0;
        endNumber = 1;
        try {

            while (true) {
                result1 = new RecursionExample().fibonnaci1(1, endNumber);
                endNumber++;
            }
        }
        catch (StackOverflowError e) {
        }

        System.out.println("Fibo 1 result:" + result1);
        System.out.println("Difference:" + result2 / result1 + "%");
    }
}
