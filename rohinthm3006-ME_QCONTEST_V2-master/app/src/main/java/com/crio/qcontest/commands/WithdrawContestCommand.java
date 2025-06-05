package com.crio.qcontest.commands;

import java.util.List;
import com.crio.qcontest.services.ContestService;

public class WithdrawContestCommand implements ICommand {

    private final ContestService contestService;

    public WithdrawContestCommand(ContestService contestService) {
        this.contestService = contestService;
    }

    @Override
    public void invoke(List<String> tokens) {
        try {
            Long contestId = Long.parseLong(tokens.get(1));
            Long userId = Long.parseLong(tokens.get(2));
            
            // Call service to withdraw the contest
            boolean success = contestService.withdrawContest(contestId, userId);
            
            // Print the appropriate message based on the success
            if (success) {
                System.out.println("User with an id " + userId + "withdrawn from contest with an id " + contestId);
            } else {
                System.out.println("Failed to withdraw user with id " + userId + " from contest with id " + contestId);
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please provide valid contestId and userId.");
        } catch (Exception e) {
            System.out.println("Error occurred while withdrawing the user from contest: " + e.getMessage());
        }
    }
}
