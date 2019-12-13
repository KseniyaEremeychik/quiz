package com.netcracker.edu.backend.controller;

import com.netcracker.edu.backend.entity.Statistic;
import com.netcracker.edu.backend.service.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class StatisticController {
    private StatisticService statisticService;

    @Autowired
    public StatisticController(StatisticService statisticService) {
        this.statisticService = statisticService;
    }

    @RequestMapping(value = "api/newStatBe", method = RequestMethod.POST)
    public Statistic addNewStatistic(@RequestBody Statistic newStat) {
        System.out.println(newStat);
        return statisticService.saveNewStat(newStat);
    }

    @RequestMapping(value = "api/userStatBe", method = RequestMethod.GET)
    public Iterable<Statistic> getUserStatistic(@RequestParam(name = "userId") Integer userId) {
        return statisticService.getUserStatistic(userId);
    }

    @RequestMapping(value = "api/fullStatBe", method = RequestMethod.GET)
    public Iterable<Statistic> getFullStatistic() {
        return statisticService.getFullStatistic();
    }
}
