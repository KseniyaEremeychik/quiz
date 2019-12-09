package com.netcracker.edu.fapi.service.impl;

import com.netcracker.edu.fapi.models.*;
import com.netcracker.edu.fapi.service.QuizDataService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class QuizDataServiceImpl implements QuizDataService {
    @Value("http://localhost:8080/")
    private String backendServerURL;

    @Override
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
    }

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
    public List<QuizViewModel> findAllQuizLike(String searchParam) {
        RestTemplate restTemplate = new RestTemplate();
        QuizViewModel[] quizViewModels = restTemplate.getForObject(backendServerURL + "api/quizLike/?searchParam=" + searchParam, QuizViewModel[].class);

        return quizViewModels == null ? Collections.emptyList(): Arrays.asList(quizViewModels);
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
        QuizViewModel quiz = new QuizViewModel();
        quiz.setCategoryId(newQuiz.getCategoryId());
        quiz.setName(newQuiz.getName());
        quiz.setQuestionNumber(newQuiz.getQuestionNumber());
        quiz.setIsConfirmed(newQuiz.getIsConfirmed());
        quiz.setCreationDate(newQuiz.getCreationDate());
        quiz.setUserId(newQuiz.getUserId());

        RestTemplate restTemplate = new RestTemplate();
        QuizViewModel savedQuiz = restTemplate.postForEntity(backendServerURL + "api/newQuiz", newQuiz, QuizViewModel.class).getBody();

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

        /*List<QuestionViewModel> questionList = new ArrayList<>(newQuiz.getQuestions());
        for (QuestionViewModel question : questionList) {
            System.out.println(question);
            for (Answer ans : question.getAnswers()) {
                System.out.println(ans);
            }
        }
        return null;*/
    }
}
