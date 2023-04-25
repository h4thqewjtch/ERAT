package com.example.calculator.check;

public class Check {

    public static void check(String value) {
        String[] parameters = value.split(" ", 3);
        if (!"/*-+".contains(parameters[1])) {
            throw new IllegalStateException();
        }
        else if (!Check.correctType(parameters[0], parameters[2])) {
            throw new IllegalArgumentException();
        }
        else if (parameters[1].equals("/") && !Check.correctValue(Double.parseDouble(parameters[2]))) {
            throw new ArithmeticException();
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