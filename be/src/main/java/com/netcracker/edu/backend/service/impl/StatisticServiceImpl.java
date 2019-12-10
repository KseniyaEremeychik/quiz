package com.netcracker.edu.backend.service.impl;

import com.netcracker.edu.backend.entity.Statistic;
import com.netcracker.edu.backend.repository.StatisticRepository;
import com.netcracker.edu.backend.service.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StatisticServiceImpl implements StatisticService {
    private StatisticRepository statisticRepository;

    @Autowired
    public StatisticServiceImpl(StatisticRepository statisticRepository) {
        this.statisticRepository = statisticRepository;
    }

    @Override
    public Statistic saveNewStat(Statistic newStat) {
        return statisticRepository.save(newStat);
    }
}
