package com.example.calculator.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class InputModel {
    @JsonProperty("first_parameter")
    private final double firstParameter;
    @JsonProperty("second_parameter")
    private final double secondParameter;
    @JsonProperty("operation")
    private final String operation;

    public InputModel(double firstParameter, double secondParameter, String operation) {

        this.firstParameter = firstParameter;
        this.secondParameter = secondParameter;
        this.operation = operation;
    }

    public double getFirstParameter() {
        return firstParameter;
    }

    public double getSecondParameter() {
        return secondParameter;
    }

    public String getOperation() {
        return operation;
    }
}
