package com.example.calculator.controller;

import com.example.calculator.models.InputModel;
import com.example.calculator.models.OutputModel;
import com.example.calculator.services.CacheService;
import com.example.calculator.controllers.MainController;
import com.example.calculator.services.MainService;
import com.example.calculator.services.StatisticService;
import org.junit.jupiter.api.Test;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Component
class MainControllerTest {
    @Test
    void PostRequest() {
        CacheService cache = new CacheService();
        MainService calculator = new MainService(cache);
        StatisticService statisticService = new StatisticService();
        MainController result = new MainController(cache, calculator, statisticService);
        List<InputModel> requestList = Arrays.asList(
                new InputModel(36, 0, "/"),
                new InputModel(45, -5, "*"),
                new InputModel(-4, 13, "-"),
                new InputModel(56, -103, "+"));
        List<OutputModel> responseList = Arrays.asList(
                new OutputModel(null),
                new OutputModel(-225.0),
                new OutputModel(-17.0),
                new OutputModel(-47.0));
        assertEquals(responseList, result.calculator(requestList));
    }
}