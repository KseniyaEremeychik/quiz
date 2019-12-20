package com.netcracker.edu.fapi.service;

import com.netcracker.edu.fapi.models.StatisticViewModel;
import org.springframework.data.domain.Page;

import java.util.List;

public interface StatisticDataService {
    StatisticViewModel addNewStatistic(StatisticViewModel newStat);

    List<StatisticViewModel> getUserStatistic(Integer userId);

    Page<StatisticViewModel> getFullStatistic(Integer page, Integer size, String sortParam, Integer sortFormat);
}
