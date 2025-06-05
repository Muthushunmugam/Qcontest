package com.crio.qcontest.commands;

import java.util.List;

import com.crio.qcontest.entities.DifficultyLevel;
import com.crio.qcontest.entities.Question;
import com.crio.qcontest.services.QuestionService;

public class ListQuestionsCommand implements ICommand {

    private final QuestionService questionService;

    public ListQuestionsCommand(QuestionService questionService) {
        this.questionService = questionService;
    }

    @Override
    public void invoke(List<String> tokens) {
        // Validate input tokens
        if (tokens.size() < 2) {
            System.out.println("Invalid command. Usage: ListQuestions <difficultyLevel>");
            return;
        }

        try {
            // Parse input
            DifficultyLevel difficultyLevel = DifficultyLevel.valueOf(tokens.get(1).toUpperCase());

            // Fetch questions
            List<Question> questions = questionService.listQuestions(difficultyLevel);

            // Print questions
            System.out.println(questions);
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid difficulty level. Please choose from: LOW, MEDIUM, HIGH.");
        } catch (Exception e) {
            System.out.println("Error occurred while listing questions: " + e.getMessage());
        }
    }
}
