package com.netcracker.edu.backend.repository;

import com.netcracker.edu.backend.entity.Question;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends CrudRepository<Question, Integer> {
    Iterable<Question> findByQuizId(Integer id);
}
