package com.example.calculator.check;

import com.example.calculator.exceptions.incorrectType;
import com.example.calculator.exceptions.incorrectValue;
import com.example.calculator.exceptions.noOperation;

public class Check {

    public static void check(String value) throws noOperation, incorrectType, incorrectValue {
        String[] parameters = value.split(" ", 3);
        if (!"/*-+".contains(parameters[1])) {
            throw new noOperation();
        }
        else if (!Check.correctType(parameters[0], parameters[2])) {
            throw new incorrectType();
        }
        else if (parameters[1].equals("/") && !Check.correctValue(Double.parseDouble(parameters[2]))) {
            throw new incorrectValue();
        }
    }

    public static boolean correctType(String var1, String var2) {
        try {
            Double.parseDouble(var1);
            Double.parseDouble(var2);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    public static boolean correctValue(double var) {
        return var !=0;
    }
}