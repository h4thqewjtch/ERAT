package com.example.calculator.counter;

public class Counter {
    private static long amount = 0;

    public static void increase() {
        amount++;
    }

    public static long getCounter() {
        return amount;
    }
}
