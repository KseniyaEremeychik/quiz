package com.netcracker.edu.fapi.service;

import com.netcracker.edu.fapi.models.StatisticViewModel;

import java.util.List;

public interface StatisticDataService {
    StatisticViewModel addNewStatistic(StatisticViewModel newStat);
    List<StatisticViewModel> getUserStatistic(Integer userId);
    List<StatisticViewModel> getFullStatistic();
}
