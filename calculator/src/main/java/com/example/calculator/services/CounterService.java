package com.example.calculator.services;

public class CounterService {
    private static long amount = 0;

    public static void increase() {
        amount++;
    }

    public static long getCounter() {
        return amount;
    }
}
