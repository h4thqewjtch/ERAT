package com.example.calculator.operation;

@FunctionalInterface
public interface Operation {
    double calculate(double x, double y);
}
