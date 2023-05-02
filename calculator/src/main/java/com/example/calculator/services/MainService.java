package com.example.calculator.services;

import com.example.calculator.models.Data;
import com.example.calculator.models.InputModel;
import com.example.calculator.repo.DataRepository;
import org.apache.juli.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.List;
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

    @Async
    public List<Double> async_calculate(List<InputModel> inputModelList) {
        synchronized (this) {
            CounterService.increase();
            LOG.log(Level.INFO, "Request number: " + CounterService.getCounter());
        }
        return inputModelList.stream().map(this::async_perform).collect(Collectors.toList());
    }

    public Double async_perform(InputModel model) {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
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
        String key = model.getFirstParameter() + model.getOperation() + model.getSecondParameter();
        if (cacheService.getCache(key) == null) {
            Operation operation = null;
            switch (model.getOperation()) {
                case "/" -> operation = div;
                case "*" -> operation = mul;
                case "-" -> operation = sub;
                case "+" -> operation = sum;
            }
            assert operation != null;
            assert dataRepository != null;
            cacheService.putCache(key, operation.calculate(model.getFirstParameter(), model.getSecondParameter()));
            dataRepository.save(new Data(model.getFirstParameter(), model.getSecondParameter(), "/",
                    operation.calculate(model.getFirstParameter(), model.getSecondParameter())));
        }
        return cacheService.getCache(key);
    }


    public List<Double> calculate(List<InputModel> inputModelList) {
        synchronized (this) {
            CounterService.increase();
            LOG.log(Level.INFO, "Request number: " + CounterService.getCounter());
        }
        return inputModelList.stream().map(this::perform).collect(Collectors.toList());
    }

    public Double perform(InputModel model) {
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
        String key = model.getFirstParameter() + model.getOperation() + model.getSecondParameter();
        if (cacheService.getCache(key) == null) {
            Operation operation = null;
            switch (model.getOperation()) {
                case "/" -> operation = div;
                case "*" -> operation = mul;
                case "-" -> operation = sub;
                case "+" -> operation = sum;
            }
            assert operation != null;
            assert dataRepository != null;
            cacheService.putCache(key, operation.calculate(model.getFirstParameter(), model.getSecondParameter()));
            dataRepository.save(new Data(model.getFirstParameter(), model.getSecondParameter(), "/",
                    operation.calculate(model.getFirstParameter(), model.getSecondParameter())));
        }
        return cacheService.getCache(key);
    }

}


