package com.crio.qcontest.commands;

import java.util.List;
import com.crio.qcontest.entities.User;
import com.crio.qcontest.services.UserService;

public class CreateUserCommand implements ICommand {

    private final UserService userService;

    public CreateUserCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void invoke(List<String> tokens) {
        if (tokens.size() != 2) {
            System.out.println("Invalid number of arguments. Expected: [CreateUser, name]");
            return;
        }

        String userName = tokens.get(1);

        // Create the user by calling the UserService
        User newUser = userService.createUser(userName);

        // Print the user's ID as a confirmation (assuming user.getId() returns the ID of the created user)
        System.out.println("User Id: " + newUser.getId());
    }
}
