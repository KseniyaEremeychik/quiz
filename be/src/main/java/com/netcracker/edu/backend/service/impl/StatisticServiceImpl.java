package com.netcracker.edu.backend.service.impl;

import com.netcracker.edu.backend.entity.Statistic;
import com.netcracker.edu.backend.repository.StatisticRepository;
import com.netcracker.edu.backend.service.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;

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

    @Override
    public Iterable<Statistic> getUserStatistic(Integer userId) {
        return statisticRepository.findByUserId(userId);
    }

    @Override
    public Page<Statistic> getFullStatistic(Integer page, Integer size, String sortParam, Integer sortFormat) {
        Pageable pageable = null;
        if (sortParam == null) {
            pageable = PageRequest.of(page, size);
        } else if (sortFormat == 1) {
            pageable = PageRequest.of(page, size, Sort.by(sortParam).ascending());
        } else {
            pageable = PageRequest.of(page, size, Sort.by(sortParam).descending());
        }

        return statisticRepository.findAll(pageable);
    }
}
