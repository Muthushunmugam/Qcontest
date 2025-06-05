package com.crio.qcontest.commands;

import java.util.List;

import com.crio.qcontest.entities.Contest;
import com.crio.qcontest.entities.DifficultyLevel;
import com.crio.qcontest.services.ContestService;

public class ListContestsCommand implements ICommand {

    private final ContestService contestService;

    public ListContestsCommand(ContestService contestService) {
        this.contestService = contestService;
    }

    @Override
    public void invoke(List<String> tokens) {
        // Validate input tokens
        if (tokens.size() < 2) {
            System.out.println("Invalid command. Usage: ListContests <difficultyLevel>");
            return;
        }

        try {
            // Parse input
            DifficultyLevel difficultyLevel = DifficultyLevel.valueOf(tokens.get(1).toUpperCase());

            // Fetch contests
            List<Contest> contests = contestService.listContests(difficultyLevel);

            // Print contests
            System.out.println(contests);
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid difficulty level. Please choose from: LOW, MEDIUM, HIGH.");
        } catch (Exception e) {
            System.out.println("Error occurred while listing contests: " + e.getMessage());
        }
    }
}
