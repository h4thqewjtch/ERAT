package com.example.calculator.services;

import com.example.calculator.models.InputModel;

public class ValidatorService {

    public static void check(InputModel model) {
        if (!"/*-+".contains(model.getOperation())) {
            throw new IllegalStateException();
        }
        else if (model.getOperation().equals("/") && !ValidatorService.correctValue(model.getSecondParameter())) {
            throw new ArithmeticException();
        }
    }

    public static boolean correctValue(double var) {
        return var !=0;
    }
}