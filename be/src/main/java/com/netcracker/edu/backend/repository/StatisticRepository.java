package com.netcracker.edu.backend.repository;

import com.netcracker.edu.backend.entity.Statistic;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatisticRepository extends CrudRepository<Statistic, Integer> {
}
