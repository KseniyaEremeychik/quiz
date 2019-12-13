package com.netcracker.edu.backend.service;

import com.netcracker.edu.backend.entity.Statistic;

import java.util.List;

public interface StatisticService {
    Statistic saveNewStat(Statistic newStat);
    Iterable<Statistic> getUserStatistic(Integer userId);
    Iterable<Statistic> getFullStatistic();
}
