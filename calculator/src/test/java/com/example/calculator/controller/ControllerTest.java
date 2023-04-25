package com.example.calculator.controller;

import com.example.calculator.cache.Cache;
import com.example.calculator.service.Calculator;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class ControllerTest {

    @Test
    void summationRightRequest() {
        Cache cache = new Cache();
        Calculator calculator = new Calculator(cache);
        Controller result = new Controller(cache, calculator);
        Stream<String[]> stream = Stream.of(new String[][]{
                {"div", "36 / -9"},
                {"mul", "36 * -9"},
                {"sub", "36 - -9"},
                {"sum", "36 + -9"},
                {"no_operation", "36 -9"},
                {"wrong_first_type", "x / -9"},
                {"wrong_second_type", "36 * y"},
                {"empty_first", " - -9"},
                {"empty_second", "36 + "}});
        Map<String, String> requestMap =
                stream.collect(Collectors.toMap(e -> e[0], e -> e[1]));
        stream = Stream.of(new String[][]{
                {"36 / -9", "-4.0"},
                {"36 * -9", "-324.0"},
                {"36 - -9", "45.0"},
                {"36 + -9", "27.0"},
                {"36 -9", "The operation not found"},
                {"x / -9", "The type of one of the parameters is incorrect"},
                {"36 * y", "The type of one of the parameters is incorrect"},
                {" - -9", "The type of one of the parameters is incorrect"},
                {"36 + ", "The type of one of the parameters is incorrect"}});
        Map<String, String> responseMap =
                stream.collect(Collectors.toMap(e -> e[0], e -> e[1]));
        assertEquals(responseMap, result.calculator(requestMap));
    }
}