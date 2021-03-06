package com.netcracker.edu.fapi.service.impl;

import com.netcracker.edu.fapi.converters.StatisticConverter;
import com.netcracker.edu.fapi.models.QuizViewModel;
import com.netcracker.edu.fapi.models.RatingViewModel;
import com.netcracker.edu.fapi.models.StatisticViewModel;
import com.netcracker.edu.fapi.models.UserViewModel;
import com.netcracker.edu.fapi.service.StatisticDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.support.PageableExecutionUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class StatisticDataServiceImpl implements StatisticDataService {
    @Value("http://localhost:8080/")
    private String backendServerURL;

    @Autowired
    private StatisticConverter statisticConverter;

    @Override
    public StatisticViewModel addNewStatistic(StatisticViewModel newStat) {
        RestTemplate restTemplate = new RestTemplate();
        StatisticViewModel statisticResponse = restTemplate.postForEntity(backendServerURL + "api/newStatBe", newStat, StatisticViewModel.class).getBody();

        RestTemplate restTemplate1 = new RestTemplate();
        RatingViewModel userGlobalRating = restTemplate1.getForObject(backendServerURL + "api/userRating/?userId=" + statisticResponse.getUserId(), RatingViewModel.class);

        RatingViewModel updateUserGlobalRating = null;
        if (userGlobalRating == null) {
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

    @Override
    public Page<StatisticViewModel> getUserStatistic(Integer userId, Integer page, Integer size, String sortParam, Integer sortFormat) {
        RestTemplate restTemplate = new RestTemplate();
        Page<StatisticViewModel> userStatistic = null;

        if (sortParam == null) {
            userStatistic = restTemplate.getForObject(backendServerURL + "api/userStatBe/?userId=" + userId + "&page=" + page + "&size=" + size, RestPageImpl.class);
        } else {
            userStatistic = restTemplate.getForObject(backendServerURL + "api/userStatBe/?userId=" + userId + "&page=" + page + "&size=" + size + "&sortParam=" + sortParam + "&sortFormat=" + sortFormat, RestPageImpl.class);
        }
        userStatistic = PageableExecutionUtils.getPage(statisticConverter.collectionTransform.apply(userStatistic.getContent()), PageRequest.of(page, size), userStatistic::getTotalElements);
        return userStatistic;
    }

    @Override
    public Page<StatisticViewModel> getFullStatistic(Integer page, Integer size, String sortParam, Integer sortFormat) {
        RestTemplate restTemplate = new RestTemplate();
        Page<StatisticViewModel> fullStatistic = null;

        if (sortParam == null) {
            fullStatistic = restTemplate.getForObject(backendServerURL + "api/fullStatBe?page=" + page + "&size=" + size, RestPageImpl.class);
        } else {
            fullStatistic = restTemplate.getForObject(backendServerURL + "api/fullStatBe?page=" + page + "&size=" + size + "&sortParam=" + sortParam + "&sortFormat=" + sortFormat, RestPageImpl.class);
        }
        fullStatistic = PageableExecutionUtils.getPage(statisticConverter.collectionTransform.apply(fullStatistic.getContent()), PageRequest.of(page, size), fullStatistic::getTotalElements);
        return fullStatistic;
    }
}
