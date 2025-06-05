package com.crio.qcontest.commands;

import java.util.List;

import com.crio.qcontest.entities.User;
import com.crio.qcontest.services.UserService;

public class LeaderBoardCommand implements ICommand {

    private final UserService userService;

    public LeaderBoardCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void invoke(List<String> tokens) {
        if (tokens.size() != 2) {
            System.out.println("Invalid number of arguments. Expected: [LeaderBoard, sortOrder]");
            return;
        }

        String sortOrder = tokens.get(1); // "DESC"
        
        // Fetch the leaderboard from UserService
        List<User> leaderboard = userService.showLeaderBoard(sortOrder);
        
        // Print leaderboard
        for (User user : leaderboard) {
            System.out.println(user.getName() + " : " + user.getScore());
        }
    }  
}
