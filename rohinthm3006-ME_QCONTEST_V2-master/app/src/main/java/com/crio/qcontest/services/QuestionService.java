package com.crio.qcontest.services;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import com.crio.qcontest.entities.DifficultyLevel;
import com.crio.qcontest.entities.Question;
import com.crio.qcontest.repositories.IQuestionRepository;

public class QuestionService{

    private final IQuestionRepository questionRepository;

    public QuestionService(IQuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    /**
     * Creates a new question with specified parameters.
     * @param title Title of the question.
     * @param level Difficulty level of the question.
     * @param difficultyScore Difficulty score of the question.
     * @return Created Question object.
     */
    
    public Question createQuestion(String title, DifficultyLevel level, Integer difficultyScore) {
        if (title == null || title.isEmpty()) {
            throw new IllegalArgumentException("Title cannot be null or empty.");
        }
        if (level == null) {
            throw new IllegalArgumentException("Difficulty level cannot be null.");
        }
        if (difficultyScore == null || difficultyScore <= 0) {
            throw new IllegalArgumentException("Difficulty score must be greater than zero.");
        }

        Question newQuestion = new Question(title, level, difficultyScore);
        questionRepository.save(newQuestion);
        return newQuestion;
    }

    /**
     * Retrieves a list of questions filtered by difficulty level.
     * @param level Difficulty level filter (can be null).
     * @return List of questions filtered by difficulty level.
     */
    

    public List<Question> listQuestions(DifficultyLevel level) {
        List<Question> allQuestions = questionRepository.findAll();
        if (level == null) {
            return allQuestions; // Return all questions if no filter is provided
        }
        // Filter questions based on difficulty level
        return allQuestions.stream()
                           .filter(question -> question.getLevel() == level)
                           .collect(Collectors.toList());
    }
}