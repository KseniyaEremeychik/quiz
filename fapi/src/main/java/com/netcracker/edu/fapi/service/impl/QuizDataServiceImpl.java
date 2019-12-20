package com.netcracker.edu.fapi.service.impl;

import com.netcracker.edu.fapi.converters.QuizConverter;
import com.netcracker.edu.fapi.models.*;
import com.netcracker.edu.fapi.service.QuizDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.support.PageableExecutionUtils;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class QuizDataServiceImpl implements QuizDataService {
    @Value("http://localhost:8080/")
    private String backendServerURL;

    @Autowired
    private QuizConverter quizConverter;

    /*@Override
    public List<QuizViewModel> findAllQuizByCategoryId(Integer id) {
        RestTemplate restTemplate = new RestTemplate();
        QuizViewModel[] quizViewModelsResponse = restTemplate.getForObject(backendServerURL + "api/quizBe/?categoryId=" + id, QuizViewModel[].class);

        List<QuizViewModel> allQuiz = quizViewModelsResponse == null ? Collections.emptyList() : Arrays.asList(quizViewModelsResponse);
        List<QuizViewModel> approvedQuiz = new ArrayList<>();
        for(int i=0; i<allQuiz.size(); i++) {
            if(allQuiz.get(i).getIsConfirmed().equals("approved")) {
                approvedQuiz.add(allQuiz.get(i));
            }
        }

        for(int i=0; i<approvedQuiz.size(); i++) {
            RestTemplate restTemplate1 = new RestTemplate();
            Integer userId =  approvedQuiz.get(i).getUserId();
            UserViewModel user = restTemplate1.getForObject(backendServerURL + "api/userBe/?userId=" + userId, UserViewModel.class);
            approvedQuiz.get(i).setUserName(user.getUserName());
        }

        return approvedQuiz;
        //return quizViewModelsResponse == null ? Collections.emptyList() : Arrays.asList(quizViewModelsResponse);
    }*/

    @Override
    public List<QuestionViewModel> getAllQuestionsByQuizId(Integer id) {
        RestTemplate restTemplate = new RestTemplate();
        QuestionViewModel[] questionViewModels = restTemplate.getForObject(backendServerURL + "api/questionsBe/?quizId=" + id, QuestionViewModel[].class);

        RestTemplate restTemplate1 = new RestTemplate();
        for(int i=0; i<questionViewModels.length; i++) {
            Answer[] answers = restTemplate1.getForObject(backendServerURL + "api/answersBe/?questionId=" + questionViewModels[i].getId(), Answer[].class);
            for(Answer ans: answers) {
                ans.setIsRight((byte)0);
            }
            questionViewModels[i].setAnswers(Arrays.asList(answers));
        }
        return questionViewModels == null ? Collections.emptyList() : Arrays.asList(questionViewModels);
    }

    @Override
    public Page<QuizViewModel> findAllQuizLike(String searchParam, Integer page, Integer size) {
        RestTemplate restTemplate = new RestTemplate();
        Page<QuizViewModel> quizPage = restTemplate.getForObject(backendServerURL + "api/quizLike/?searchParam=" + searchParam + "&page=" + page + "&size=" + size, RestPageImpl.class);

        quizPage = PageableExecutionUtils.getPage(quizConverter.collectionTransform.apply(quizPage.getContent()), PageRequest.of(page, size), quizPage::getTotalElements);

        return quizPage;
    }

    @Override
    public List<QuizViewModel> findAllQuizByUserId(Integer id) {
        RestTemplate restTemplate = new RestTemplate();
        QuizViewModel[] quizViewModels = restTemplate.getForObject(backendServerURL + "api/quizByUser/?userId=" + id, QuizViewModel[].class);

        for(int i=0; i < quizViewModels.length; i++) {
            RestTemplate restTemplate1 = new RestTemplate();
            CategoryViewModel category = restTemplate1.getForObject(backendServerURL + "api/categoryName/?categoryId=" + quizViewModels[i].getCategoryId(), CategoryViewModel.class);
            quizViewModels[i].setCategoryName(category.getName());
        }

        return quizViewModels == null ? Collections.emptyList(): Arrays.asList(quizViewModels);
    }

    @Override
    public QuizWithQuestionsModel saveNewQuiz(QuizWithQuestionsModel newQuiz) {
        if(!validateNewQuiz(newQuiz.getQuestions())) {
            return null;
        } else {
            QuizViewModel quiz = new QuizViewModel();
            quiz.setCategoryId(newQuiz.getCategoryId());
            quiz.setName(newQuiz.getName());
            quiz.setQuestionNumber(newQuiz.getQuestionNumber());
            quiz.setIsConfirmed(newQuiz.getIsConfirmed());
            quiz.setCreationDate(newQuiz.getCreationDate());
            quiz.setUserId(newQuiz.getUserId());

            RestTemplate restTemplate = new RestTemplate();
            QuizViewModel savedQuiz = restTemplate.postForEntity(backendServerURL + "api/newQuiz", quiz, QuizViewModel.class).getBody();

            List<QuestionViewModel> questionList = new ArrayList<>(newQuiz.getQuestions());
            List<QuestionViewModel> savedQuestionList = new ArrayList<>();
            for(QuestionViewModel question: questionList) {
                question.setQuizId(savedQuiz.getId());

                RestTemplate restTemplate1 = new RestTemplate();
                QuestionViewModel qst = restTemplate1.postForEntity(backendServerURL + "api/saveQuestion", question, QuestionViewModel.class).getBody();

                List<Answer> savedAnswers = new ArrayList<>();
                for(Answer ans: question.getAnswers()) {
                    ans.setQuestionId(qst.getId());

                    RestTemplate restTemplate2 = new RestTemplate();
                    Answer answer = restTemplate2.postForEntity(backendServerURL + "api/saveAnswer", ans, Answer.class).getBody();
                    savedAnswers.add(answer);
                }
                qst.setAnswers(savedAnswers);
                savedQuestionList.add(qst);
            }

            QuizWithQuestionsModel savedQuizWithQuestions = new QuizWithQuestionsModel();

            savedQuizWithQuestions.setId(savedQuiz.getId());
            savedQuizWithQuestions.setCategoryId(savedQuiz.getCategoryId());
            savedQuizWithQuestions.setName(savedQuiz.getName());
            savedQuizWithQuestions.setQuestionNumber(savedQuiz.getQuestionNumber());
            savedQuizWithQuestions.setIsConfirmed(savedQuiz.getIsConfirmed());
            savedQuizWithQuestions.setCreationDate(savedQuiz.getCreationDate());
            savedQuizWithQuestions.setUserId(savedQuiz.getUserId());
            savedQuizWithQuestions.setQuestions(savedQuestionList);

            return savedQuizWithQuestions;
        }
    }

    @Override
    public QuizViewModel editQuizStatus(QuizViewModel quiz) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForEntity(backendServerURL + "api/newQuiz", quiz, QuizViewModel.class).getBody();
    }

    @Override
    public void deleteQuizById(Integer quizId) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(backendServerURL + "/api/deleteQuizBe/" + quizId);
    }

    @Override
    public Page<QuizViewModel> getQuizByPage(Integer categoryId, Integer page, Integer size) {
        RestTemplate restTemplate = new RestTemplate();
        Page<QuizViewModel> quiz =restTemplate.getForObject(backendServerURL + "api/quizPage/?categoryId=" + categoryId + "&page=" + page + "&size=" + size, RestPageImpl.class);

        quiz = PageableExecutionUtils.getPage(quizConverter.collectionTransform.apply(quiz.getContent()), PageRequest.of(page, size), quiz::getTotalElements);

        return quiz;
    }

    @Override
    public Page<QuizViewModel> getQuizByPageAndStatus(Integer categoryId, Integer page, Integer size, String status) {
        RestTemplate restTemplate = new RestTemplate();
        Page<QuizViewModel> quiz = restTemplate.getForObject(backendServerURL + "api/quizPageAndStatus/?categoryId=" + categoryId + "&page=" + page + "&size=" + size + "&status=" + status, RestPageImpl.class);

        quiz = PageableExecutionUtils.getPage(quizConverter.collectionTransform.apply(quiz.getContent()), PageRequest.of(page, size), quiz::getTotalElements);

        return quiz;
    }

    @Override
    public Page<QuizViewModel> getAllQuiz(Integer page, Integer size, String sortParam, Integer sortFormat) {
        Page<QuizViewModel> quizList = null;
        RestTemplate restTemplate = new RestTemplate();
        if(sortParam == null) {
            quizList = restTemplate.getForObject(backendServerURL + "api/allQuizBe/?page=" + page + "&size=" + size, RestPageImpl.class);
        } else {
            quizList = restTemplate.getForObject(backendServerURL + "api/allQuizBe/?page=" + page + "&size=" + size + "&sortParam=" + sortParam + "&sortFormat=" + sortFormat, RestPageImpl.class);
        }
        quizList = PageableExecutionUtils.getPage(quizConverter.collectionTransform.apply(quizList.getContent()), PageRequest.of(page, size), quizList::getTotalElements);
        return quizList;
    }

    @Override
    public Page<QuizViewModel> getAllQuizWithStatus(Integer page, Integer size, String status, String sortParam, Integer sortFormat) {
        RestTemplate restTemplate = new RestTemplate();
        Page<QuizViewModel> quizList = null;

        if(sortParam == null) {
            quizList = restTemplate.getForObject(backendServerURL + "api/allQuizWithStatus/?page=" + page + "&size=" + size + "&status=" + status, RestPageImpl.class);
        } else {
            quizList = restTemplate.getForObject(backendServerURL + "api/allQuizWithStatus/?page=" + page + "&size=" + size + "&status=" + status + "&sortParam=" + sortParam + "&sortFormat=" + sortFormat, RestPageImpl.class);
        }
        quizList = PageableExecutionUtils.getPage(quizConverter.collectionTransform.apply(quizList.getContent()), PageRequest.of(page, size), quizList::getTotalElements);
        return quizList;
    }

    private boolean validateNewQuiz(List<QuestionViewModel> questions) {
        for(QuestionViewModel question: questions) {
            if(question.getText().length() == 0 || question.getText().length() > 250 || !question.getText().matches("^[a-zA-Z0-9!?,._\\s-]+$")) {
                return false;
            } else {
                for(Answer answer: question.getAnswers()) {
                    if(answer.getText().length() == 0 || answer.getText().length() > 100 || !answer.getText().matches("^[a-zA-Z0-9!?,._\\s-]+$")) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
