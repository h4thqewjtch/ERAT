package com.example.calculator.models;

public class StatisticModel {
    private double max = 0;
    private double min = 0;
    private double average = 0;

    public void setMax(double max) {
        this.max = max;
    }

    public void setMin(double min) {
        this.min = min;
    }

    public void setAverage(double average) {
        this.average = average;
    }
    public double getMax() {
        return max;
    }

    public double getMin() {
        return min;
    }

    public double getAverage() {
        return average;
    }
}
