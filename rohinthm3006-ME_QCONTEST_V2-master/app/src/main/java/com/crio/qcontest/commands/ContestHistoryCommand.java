package com.crio.qcontest.commands;

import java.util.List;

import com.crio.qcontest.entities.Contestant;
import com.crio.qcontest.entities.Question;
import com.crio.qcontest.services.ContestService;

public class ContestHistoryCommand implements ICommand {

    private final ContestService contestService;

    public ContestHistoryCommand(ContestService contestService) {
        this.contestService = contestService;
    }

    @Override
    public void invoke(List<String> tokens) {
        // Validate input tokens
        if (tokens.size() < 2) {
            System.out.println("Invalid command. Usage: ContestHistory <contestId>");
            return;
        }

        try {
            // Parse contestId from tokens
            Long contestId = Long.parseLong(tokens.get(1));

            // Get contest history
            List<Contestant> contestants = contestService.contestHistory(contestId);

            // Sort contestants by their total score in descending order
            contestants.sort((c1, c2) -> c2.getTotalScore().compareTo(c1.getTotalScore()));

            // Build and print the result
            StringBuilder result = new StringBuilder();
            for (Contestant contestant : contestants) {
                result.append(contestant.getUser().getName()).append(" : ")
                      .append(contestant.getTotalScore()).append(" [");

                List<Question> solvedQuestions = contestant.getSolvedQuestions();
                for (int i = 0; i < solvedQuestions.size(); i++) {
                    result.append(solvedQuestions.get(i).getId());
                    if (i < solvedQuestions.size() - 1) {
                        result.append(",");
                    }
                }
                result.append("]\n");
            }

            // Print result to the console
            System.out.print(result.toString().trim());
        } catch (NumberFormatException e) {
            System.out.println("Invalid contestId. Please provide a valid number.");
        } catch (Exception e) {
            System.out.println("Error occurred while retrieving contest history: " + e.getMessage());
        }
    }
}

