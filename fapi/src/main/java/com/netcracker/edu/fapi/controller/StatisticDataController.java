package com.netcracker.edu.fapi.controller;

import com.netcracker.edu.fapi.models.StatisticViewModel;
import com.netcracker.edu.fapi.service.StatisticDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StatisticDataController {
    @Autowired
    StatisticDataService statisticDataService;

    @RequestMapping(value = "/api/newStat", method = RequestMethod.POST)
    public ResponseEntity<StatisticViewModel> addNewStatistic(@RequestBody StatisticViewModel newStat) {
        if (newStat != null) {
            return ResponseEntity.ok(statisticDataService.addNewStatistic(newStat));
        }
        return null;
    }

    @RequestMapping(value = "/api/userStat", method = RequestMethod.GET)
    public ResponseEntity<List<StatisticViewModel>> getUserStatistic(@RequestParam(name = "userId") Integer userId) {
        return ResponseEntity.ok(statisticDataService.getUserStatistic(userId));
    }

    @RequestMapping(value = "/api/fullStat", method = RequestMethod.GET)
    public Page<StatisticViewModel> getFullStatistic(@RequestParam(name = "page") Integer page,
                                                     @RequestParam(name = "size") Integer size,
                                                     @RequestParam(name = "sortParam", required = false) String sortParam,
                                                     @RequestParam(name = "sortFormat", required = false) Integer sortFormat) {
        return statisticDataService.getFullStatistic(page, size, sortParam, sortFormat);
    }
}
