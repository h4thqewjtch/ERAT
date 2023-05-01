package com.example.calculator.services;

@FunctionalInterface
public interface Operation {
    double calculate(double x, double y);
}
