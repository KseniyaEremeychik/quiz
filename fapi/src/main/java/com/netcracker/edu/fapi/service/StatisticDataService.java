package com.netcracker.edu.fapi.service;

import com.netcracker.edu.fapi.models.StatisticViewModel;
import org.springframework.data.domain.Page;

import java.util.List;

public interface StatisticDataService {
    StatisticViewModel addNewStatistic(StatisticViewModel newStat);

    Page<StatisticViewModel> getUserStatistic(Integer userId, Integer page, Integer size, String sortParam, Integer sortFormat);

    Page<StatisticViewModel> getFullStatistic(Integer page, Integer size, String sortParam, Integer sortFormat);
}
