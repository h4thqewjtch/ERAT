package com.example.calculator.controller;

import com.example.calculator.cache.Cache;
import com.example.calculator.service.Calculator;

import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
public class Controller {
    private final Cache cache;
    private final Calculator calculator;

    public Controller(Cache cache, Calculator calculator) {
        this.cache = cache;
        this.calculator = calculator;
    }

    @GetMapping("evict")
    public String evict() {
        return cache.evict();
    }

    @GetMapping("manual")
    public Map<String, Double> getManualData() {
        return cache.getMap();
    }

    @PostMapping("calc")
    public Map<String, String> calculator(@RequestBody Map<String, String> requestMap) {
        Map<String, String> responseMap = requestMap.entrySet().stream()
                // собираем новую карту:      ключ - значение,   значение - результат выполнения
                .collect(Collectors.toMap(Map.Entry::getValue, v -> calculator.perform(v.getValue())));
        return responseMap.entrySet().stream().sorted(Map.Entry.comparingByValue())
                .collect(LinkedHashMap::new, (m, c) -> m.put(c.getKey(), c.getValue()), LinkedHashMap::putAll);
    }
}