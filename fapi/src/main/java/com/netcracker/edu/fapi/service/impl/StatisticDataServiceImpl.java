package com.netcracker.edu.fapi.service.impl;

import com.netcracker.edu.fapi.models.RatingViewModel;
import com.netcracker.edu.fapi.models.StatisticViewModel;
import com.netcracker.edu.fapi.service.StatisticDataService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.DecimalFormat;

@Service
public class StatisticDataServiceImpl implements StatisticDataService {
    @Value("http://localhost:8080/")
    private String backendServerURL;

    @Override
    public StatisticViewModel addNewStatistic(StatisticViewModel newStat) {
        RestTemplate restTemplate = new RestTemplate();
        StatisticViewModel statisticResponse = restTemplate.postForEntity(backendServerURL + "api/newStatBe", newStat, StatisticViewModel.class).getBody();

        RestTemplate restTemplate1 = new RestTemplate();
        RatingViewModel userGlobalRating = restTemplate1.getForObject(backendServerURL + "api/userRating/?userId=" + statisticResponse.getUserId(), RatingViewModel.class);

        RatingViewModel updateUserGlobalRating = null;
        if(userGlobalRating == null) {
            updateUserGlobalRating = new RatingViewModel(0, statisticResponse.getUserId(), statisticResponse.getRightAnswersPercent(), 1);
        } else {
            int quizNum = userGlobalRating.getQuizNum();
            double newPercent = (userGlobalRating.getPercent() * quizNum + statisticResponse.getRightAnswersPercent()) / (quizNum + 1);
            double newFormattedPercent = Double.valueOf(new DecimalFormat(".##").format(newPercent));
            updateUserGlobalRating = new RatingViewModel(userGlobalRating.getId(), statisticResponse.getUserId(), newFormattedPercent, quizNum + 1);
        }

        RestTemplate restTemplate2 = new RestTemplate();
        RatingViewModel updatedRatingResponse = restTemplate2.postForEntity(backendServerURL + "api/saveRating", updateUserGlobalRating, RatingViewModel.class).getBody();

        return statisticResponse;
    }
}
