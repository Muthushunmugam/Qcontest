package com.crio.qcontest.commands;

import java.util.List;
import com.crio.qcontest.services.ContestService;

public class RunContestCommand implements ICommand {

    private final ContestService contestService;

    public RunContestCommand(ContestService contestService) {
        this.contestService = contestService;
    }

    @Override
    public void invoke(List<String> tokens) {
        // Ensure tokens have enough arguments
        if (tokens.size() >= 3) {
            try {
                Long contestId = Long.valueOf(tokens.get(1));  // Extract contest ID
                Long contestCreator = Long.valueOf(tokens.get(2));  // Extract contest creator ID
                contestService.runContest(contestId, contestCreator);  // Call the service method
            } catch (NumberFormatException e) {
                // Handle invalid Long value conversion
                System.out.println("Invalid contest ID or contest creator ID");
            }
        } else {
            // Handle the case where there are not enough tokens
            System.out.println("Insufficient arguments. Expected contestId and contestCreator.");
        }
    }
}
