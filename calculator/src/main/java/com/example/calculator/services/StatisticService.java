package com.example.calculator.services;

import com.example.calculator.models.InputModel;
import com.example.calculator.models.StatisticModel;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class StatisticService {
    public List<StatisticModel> analise(List<InputModel> inputModelList) {
        return inputModelList.stream().map(model -> {
            StatisticModel statisticModel = new StatisticModel();
            statisticModel.setMax(countMax(model));
            statisticModel.setMin(countMin(model));
            statisticModel.setAverage(countAverage(model));
            return statisticModel;
        }).collect(Collectors.toList());
    }

    public double countAverage(InputModel model) {
        return (model.getFirstParameter() + model.getSecondParameter()) / 2;
    }

    private double countMin(InputModel model) {
        List<Double> list = Arrays.asList(model.getFirstParameter(), model.getSecondParameter());
        return Collections.min(list);
    }

    private double countMax(InputModel model) {
        List<Double> list = Arrays.asList(model.getFirstParameter(), model.getSecondParameter());
        return Collections.max(list);
    }

}
