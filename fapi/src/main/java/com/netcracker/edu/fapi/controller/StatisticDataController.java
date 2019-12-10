package com.netcracker.edu.fapi.controller;

import com.netcracker.edu.fapi.models.StatisticViewModel;
import com.netcracker.edu.fapi.service.StatisticDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StatisticDataController {
    @Autowired
    StatisticDataService statisticDataService;

    @RequestMapping(value="/api/newStat", method = RequestMethod.POST)
    public ResponseEntity<StatisticViewModel> addNewStatistic(@RequestBody StatisticViewModel newStat) {
        if(newStat != null) {
            return ResponseEntity.ok(statisticDataService.addNewStatistic(newStat));
        }
        return null;
    }
}
