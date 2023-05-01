package com.example.calculator.services;

import com.example.calculator.models.Data;
import com.example.calculator.models.InputModel;
import com.example.calculator.repo.DataRepository;
import org.apache.juli.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Component
public class MainService {
    private final CacheService cacheService;
    private final Logger LOG = Logger.getLogger(Log.class.getName());
    @Autowired
    private DataRepository dataRepository;
    Operation div = (x, y) -> x / y;
    Operation mul = (x, y) -> x * y;
    Operation sub = (x, y) -> x - y;
    Operation sum = Double::sum;

    public MainService(CacheService cacheService) {
        this.cacheService = cacheService;
    }

    public List<Double> calculate(List<InputModel> inputModelList) {
        synchronized (this) {
            CounterService.increase();
            LOG.log(Level.INFO, "Request number: " + CounterService.getCounter());
        }
        return inputModelList.stream().map(this::perform).collect(Collectors.toList());
    }

    @Async
    public CompletableFuture<List<Double>> async_calculate(List<InputModel> inputModelList) {
        synchronized (this) {
            CounterService.increase();
            LOG.log(Level.INFO, "Request number: " + CounterService.getCounter());
        }
        return CompletableFuture.completedFuture(inputModelList.stream().map(this::perform).collect(Collectors.toList()));
    }

    public Double perform(InputModel model) {
        String key = model.getFirstParameter() + model.getOperation() + model.getSecondParameter();
        try {
            ValidatorService.check(model);
        } catch (IllegalStateException error) {
            LOG.log(Level.WARNING, "BAD REQUEST: The operation not found");
            return null;
        } catch (ArithmeticException error) {
            LOG.log(Level.WARNING, "INTERNAL SERVER ERROR: The value of the second parameter is incorrect");
            return null;
        }
        LOG.log(Level.INFO, "Request was successfully processed");
        if (cacheService.getCache(key) == null) {
            switch (model.getOperation()) {
                case "/" -> {
                    cacheService.putCache(key, div.calculate(model.getFirstParameter(), model.getSecondParameter()));
                    dataRepository.save(new Data(model.getFirstParameter(), model.getSecondParameter(), "/",
                            div.calculate(model.getFirstParameter(), model.getSecondParameter())));
                }
                case "*" -> {
                    cacheService.putCache(key, mul.calculate(model.getFirstParameter(), model.getSecondParameter()));
                    dataRepository.save(new Data(model.getFirstParameter(), model.getSecondParameter(), "*",
                            mul.calculate(model.getFirstParameter(), model.getSecondParameter())));
                }
                case "-" -> {
                    cacheService.putCache(key, sub.calculate(model.getFirstParameter(), model.getSecondParameter()));
                    dataRepository.save(new Data(model.getFirstParameter(), model.getSecondParameter(), "-",
                            sub.calculate(model.getFirstParameter(), model.getSecondParameter())));
                }
                case "+" -> {
                    cacheService.putCache(key, sum.calculate(model.getFirstParameter(), model.getSecondParameter()));
                    dataRepository.save(new Data(model.getFirstParameter(), model.getSecondParameter(), "+",
                            sum.calculate(model.getFirstParameter(), model.getSecondParameter())));
                }
            }
        }
        return cacheService.getCache(key);
    }
}


