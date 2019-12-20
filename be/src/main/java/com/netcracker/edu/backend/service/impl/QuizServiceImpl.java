package com.netcracker.edu.backend.service.impl;

import com.netcracker.edu.backend.entity.Quiz;
import com.netcracker.edu.backend.repository.QuizRepository;
import com.netcracker.edu.backend.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class QuizServiceImpl implements QuizService {
    private QuizRepository quizRepository;

    @Autowired
    public QuizServiceImpl(QuizRepository quizRepository) {
        this.quizRepository = quizRepository;
    }

    @Override
    public Iterable<Quiz> findAllQuizByCategoryId(Integer id) {
        return quizRepository.findByCategoryId(id);
    }

    /*@Override
    public Iterable<Quiz> findAllQuizLike(String searchParam) {
        return quizRepository.findByNameContaining(searchParam);
    }*/

    /*@Override
    public Page<Quiz> findAllQuizLike(String searchParam, Integer page, Integer size) {
        return quizRepository.findByNameContaining(searchParam, PageRequest.of(page, size));
    }*/

    /*@Override
    public Page<Quiz> findAllQuizLike(String searchParam, Integer page, Integer size, String status) {
        return quizRepository.findByNameContainingAndIsConfirmed(searchParam, status, PageRequest.of(page, size));
    }*/

    @Override
    public Iterable<Quiz> findAllQuizByUserId(Integer id) {
        return quizRepository.findByUserId(id);
    }

    @Override
    public Quiz saveQuiz(Quiz quiz) {
        return quizRepository.save(quiz);
    }

    @Override
    public void deleteQuizById(Integer quizId) {
        quizRepository.deleteById(quizId);
    }

    @Override
    public Optional<Quiz> getQuizById(Integer id) {
        return quizRepository.findById(id);
    }

    @Override
    public Page<Quiz> getQuizByPage(Integer categoryId, Integer page, Integer size) {
        return quizRepository.findByCategoryId(categoryId, PageRequest.of(page, size));
    }

    @Override
    public Page<Quiz> findAllQuizLike(String searchParam, Integer page, Integer size) {
        return quizRepository.findByIsConfirmedAndNameContaining(Quiz.Confirmation.approved, searchParam, PageRequest.of(page, size));
    }

    @Override
    public Page<Quiz> getQuizByPageAndStatus(String status, Integer categoryId, Integer page, Integer size) {
        if(status.equals("approved")) {
            return quizRepository.findByCategoryIdAndIsConfirmed(categoryId, Quiz.Confirmation.approved, PageRequest.of(page, size));
        } else {
            return quizRepository.findByCategoryIdAndIsConfirmed(categoryId, Quiz.Confirmation.unseen, PageRequest.of(page, size));
        }
    }

    @Override
    public Page<Quiz> getAll(Integer page, Integer size, String sortParam, Integer sortFormat) {
        if (sortParam == null) {
            return quizRepository.findAll(PageRequest.of(page, size));
        } else if(sortFormat == 1) {
            return quizRepository.findAll(PageRequest.of(page, size, Sort.by(sortParam).ascending()));
        } else {
            return quizRepository.findAll(PageRequest.of(page, size, Sort.by(sortParam).descending()));
        }
    }

    @Override
    public Page<Quiz> getAllQuizWithStatus(Integer page, Integer size, String status, String sortParam, Integer sortFormat) {
        Pageable pageable = null;
        if(sortParam == null) {
            pageable = PageRequest.of(page, size);
        } else if(sortFormat == 1){
            pageable = PageRequest.of(page, size, Sort.by(sortParam).ascending());
        } else {
            pageable = PageRequest.of(page, size, Sort.by(sortParam).descending());
        }

        if(status.equals("approved")) {
            return quizRepository.findByIsConfirmed(Quiz.Confirmation.approved, pageable);
        } else {
            return quizRepository.findByIsConfirmed(Quiz.Confirmation.unseen, pageable);
        }
    }
}
