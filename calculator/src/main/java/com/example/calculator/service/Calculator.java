package com.example.calculator.service;

import com.example.calculator.cache.Cache;
import com.example.calculator.check.Check;
import com.example.calculator.counter.Counter;

import com.example.calculator.operation.Operation;
import org.apache.juli.logging.Log;
import org.springframework.stereotype.Component;

import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class Calculator {
    private final Logger LOG = Logger.getLogger(Log.class.getName());
    private final Cache cache;
    Operation div = (x, y) -> x / y;
    Operation mul = (x, y) -> x * y;
    Operation sub = (x, y) -> x - y;
    Operation sum = (x, y) -> x + y;

    public Calculator(Cache cache) {
        this.cache = cache;
    }

    public String perform(String key) {
        synchronized (this) {
            Counter.increase();
            LOG.log(Level.INFO, "Request number: " + Counter.getCounter());
        }
        try {
            Check.check(key);
        } catch (IllegalStateException error) {
            LOG.log(Level.WARNING, "BAD REQUEST: The operation not found");
            return "The operation not found";
        } catch (IllegalArgumentException error) {
            LOG.log(Level.WARNING, "BAD REQUEST: The type of one of the parameters is incorrect");
            return "The type of one of the parameters is incorrect";
        } catch (ArithmeticException error) {
            LOG.log(Level.WARNING, "INTERNAL SERVER ERROR: The value of the second parameter is incorrect");
            return "The value of the second parameter is incorrect";
        }
        LOG.log(Level.INFO, "Request was successfully processed");
        String[] parameters = key.split(" ", 3);
        if (cache.getCache(key).equals("null")) {
            switch (parameters[1]) {
                case "/" -> cache.putCache(key, div.calculate(Double.parseDouble(parameters[0]),
                        Double.parseDouble(parameters[2])));
                case "*" -> cache.putCache(key, mul.calculate(Double.parseDouble(parameters[0]),
                        Double.parseDouble(parameters[2])));
                case "-" -> cache.putCache(key, sub.calculate(Double.parseDouble(parameters[0]),
                        Double.parseDouble(parameters[2])));
                case "+" -> cache.putCache(key, sum.calculate(Double.parseDouble(parameters[0]),
                        Double.parseDouble(parameters[2])));
            }
        }
        return cache.getCache(key);
    }
}
