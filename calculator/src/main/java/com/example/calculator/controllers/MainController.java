package com.example.calculator.controllers;

import com.example.calculator.models.Data;
import com.example.calculator.models.InputModel;
import com.example.calculator.models.OutputModel;
import com.example.calculator.models.StatisticModel;
import com.example.calculator.repo.DataRepository;
import com.example.calculator.services.CacheService;
import com.example.calculator.services.MainService;

import com.example.calculator.services.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
public class MainController {
    private final CacheService cacheService;
    private final MainService mainService;
    private final StatisticService statisticService;

    @Autowired
    private DataRepository dataRepository;

    public MainController(CacheService cacheService, MainService calculator, StatisticService statisticService) {
        this.cacheService = cacheService;
        this.mainService = calculator;
        this.statisticService = statisticService;
    }

    @GetMapping("/evict")
    public String evict() {
        return cacheService.evict();
    }

    @GetMapping("/cache")
    public Map<String, Double> getManualCache() {
        return cacheService.getMap();
    }

    @GetMapping("/delete")
    public String delete() {
        dataRepository.deleteAll();
        return "Current Status : All data entries have been deleted!";
    }

    @GetMapping("/data")
    public Iterable<Data> getManualData() {
        return dataRepository.findAll();
    }

    @PostMapping("/calculate")
    public List<OutputModel> calculator(@RequestBody List<InputModel> inputModelList) {
        return mainService.calculate(inputModelList).stream().map(OutputModel::new).collect(Collectors.toList());
    }

    @PostMapping("/statistic")
    public List<StatisticModel> statistic(@RequestBody List<InputModel> inputModelList) {
        return statisticService.analise(inputModelList);
    }
}