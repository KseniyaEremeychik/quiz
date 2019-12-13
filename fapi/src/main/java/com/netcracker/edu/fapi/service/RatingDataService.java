package com.netcracker.edu.fapi.service;

import com.netcracker.edu.fapi.models.RatingViewModel;

import java.util.List;
import java.util.Map;

public interface RatingDataService {
    List<RatingViewModel> getTopTen();
}
