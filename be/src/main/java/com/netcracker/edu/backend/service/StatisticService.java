package com.netcracker.edu.backend.service;

import com.netcracker.edu.backend.entity.Statistic;
import org.springframework.data.domain.Page;

import java.util.List;

public interface StatisticService {
    Statistic saveNewStat(Statistic newStat);

    Page<Statistic> getUserStatistic(Integer userId, Integer page, Integer size, String sortParam, Integer sortFormat);

    Page<Statistic> getFullStatistic(Integer page, Integer size, String sortParam, Integer sortFormat);
}
