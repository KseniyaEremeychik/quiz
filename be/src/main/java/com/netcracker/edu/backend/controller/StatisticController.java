package com.netcracker.edu.backend.controller;

import com.netcracker.edu.backend.entity.Statistic;
import com.netcracker.edu.backend.service.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StatisticController {
    private StatisticService statisticService;

    @Autowired
    public StatisticController(StatisticService statisticService) {
        this.statisticService = statisticService;
    }

    @RequestMapping(value = "api/newStatBe", method = RequestMethod.POST)
    public Statistic addNewStatistic(@RequestBody Statistic newStat) {
        return statisticService.saveNewStat(newStat);
    }
}
