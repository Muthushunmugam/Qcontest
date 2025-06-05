package com.crio.qcontest.commands;

import java.util.List;

import com.crio.qcontest.entities.DifficultyLevel;
import com.crio.qcontest.entities.Question;
import com.crio.qcontest.services.QuestionService;

public class CreateQuestionCommand implements ICommand{
    private final QuestionService questionService;

    public CreateQuestionCommand(QuestionService questionService) {
        this.questionService = questionService;
    }

    @Override
public void invoke(List<String> tokens) {
    // Validate input tokens
    if (tokens.size() < 4) {
        System.out.println("Invalid command. Usage: CreateQuestion <questionText> <difficultyLevel> <score>");
        return;
    }

    try {
        // Parse inputs
        String questionText = tokens.get(1);
        DifficultyLevel difficultyLevel = DifficultyLevel.valueOf(tokens.get(2).toUpperCase());
        int score = Integer.parseInt(tokens.get(3));

        // Validate score
        if (score <= 0) {
            System.out.println("Invalid score. It must be a positive integer.");
            return;
        }

        // Create question
        Question question = questionService.createQuestion(questionText, difficultyLevel, score);

        // Print only the question ID
        System.out.println("Question Id: " + question.getId());
    } catch (IllegalArgumentException e) {
        System.out.println("Invalid difficulty level. Please choose from: LOW, MEDIUM, HIGH.");
    } catch (Exception e) {
        System.out.println("Error occurred while creating the question: " + e.getMessage());
    }
}


}
