package com.netcracker.edu.backend.repository;

import com.netcracker.edu.backend.entity.Answer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswerRepository extends CrudRepository<Answer, Integer> {
    Iterable<Answer> findByQuestionId(Integer id);
    Answer findByQuestionIdAndIsRight(Integer id, byte isRight);
}
