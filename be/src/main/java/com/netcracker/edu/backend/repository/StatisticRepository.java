package com.netcracker.edu.backend.repository;

import com.netcracker.edu.backend.entity.Statistic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatisticRepository extends PagingAndSortingRepository<Statistic, Integer> {
    Iterable<Statistic> findByUserId(Integer userId);
}
