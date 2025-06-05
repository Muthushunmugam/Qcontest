package com.crio.qcontest.commands;

import java.util.List;

import com.crio.qcontest.entities.Contestant;
import com.crio.qcontest.services.ContestService;

public class AttendContestCommand implements ICommand {

    private final ContestService contestService;

    public AttendContestCommand(ContestService contestService) {
        this.contestService = contestService;
    }

    @Override
    public void invoke(List<String> tokens) {
        try {
            // Parse the tokens
            Long contestId = Long.parseLong(tokens.get(1));
            Long userId = Long.parseLong(tokens.get(2));

            // Call the service to attend the contest
            Contestant contestant = contestService.attendContest(contestId, userId);

            // Print the result (toString of Contestant)
            System.out.println(contestant);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input format. Contest ID and User ID must be numbers.");
        } catch (Exception e) {
            System.out.println(e.getMessage()); // Print the exception message from the service
        }
    }
}
