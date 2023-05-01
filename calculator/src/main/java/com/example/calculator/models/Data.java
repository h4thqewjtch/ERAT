package com.example.calculator.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Data {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double firstParameter;
    private double secondParameter;
    private String operation;
    private double result;

    public Data() {
    }

    public Data(double firstParameter, double secondParameter, String operation, double result) {
        this.firstParameter = firstParameter;
        this.secondParameter = secondParameter;
        this.operation = operation;
        this.result = result;
    }

    public void setFirstParameter(double firstParameter) {
        this.firstParameter = firstParameter;
    }

    public double getFirstParameter() {
        return firstParameter;
    }

    public void setSecondParameter(double secondParameter) {
        this.secondParameter = secondParameter;
    }

    public double getSecondParameter() {
        return secondParameter;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getOperation() {
        return operation;
    }

    public void setResult(double result) {
        this.result = result;
    }

    public double getResult() {
        return result;
    }
}
