package com.crio.qcontest.commands;

import java.util.List;

import com.crio.qcontest.entities.Contest;
import com.crio.qcontest.entities.DifficultyLevel;
import com.crio.qcontest.services.ContestService;

public class CreateContestCommand implements ICommand {

    private final ContestService contestService; 

    public CreateContestCommand(ContestService contestService) {
        this.contestService = contestService;
    }

    @Override
public void invoke(List<String> tokens) {
    // Validate input tokens
    if (tokens.size() < 5) {
        System.out.println("Invalid command. Usage: CreateContest <contestName> <difficultyLevel> <creatorId> <numOfQuestions>");
        return;
    }

    try {
        // Parse inputs
        String contestName = tokens.get(1);
        DifficultyLevel difficultyLevel = DifficultyLevel.valueOf(tokens.get(2).toUpperCase());
        Long creatorId = Long.parseLong(tokens.get(3));
        Integer numOfQuestions = Integer.parseInt(tokens.get(4));

        // Create contest
        Contest contest = contestService.createContest(contestName, difficultyLevel, creatorId, numOfQuestions);

        // Print only the contest ID
        System.out.println("Contest Id: " + contest.getId());
    } catch (NumberFormatException e) {
        System.out.println("Invalid input. Please ensure creatorId and numOfQuestions are valid numbers.");
    } catch (IllegalArgumentException e) {
        System.out.println("Invalid difficulty level. Please choose from: LOW, MEDIUM, HIGH.");
    } catch (Exception e) {
        System.out.println("Error occurred while creating the contest: " + e.getMessage());
    }
}


}