package com.crio.qcontest.services;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.crio.qcontest.entities.User;
import com.crio.qcontest.repositories.IUserRepository;

public class UserService {

    private final IUserRepository userRepository;

    public UserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Creates a new user with the specified name.
     * @param name Name of the user.
     * @return Created User object.
     */
    
    public User createUser(String name) {
        User user = new User(name);
        return userRepository.save(user);
    }
    

    /**
     * Retrieves a list of users sorted by their score.
     * @param order Sorting order ("ASC" for ascending, "DESC" for descending).
     * @return List of users sorted by score as per the specified order.
     */
    public List<User> showLeaderBoard(String order) {
        // Retrieve all users
        List<User> users = userRepository.findAll();

        // Sort the users by score based on the specified order
        if (order != null && order.equals("ASC")) {
            Collections.sort(users, Comparator.comparingInt(User::getScore));
        } else if (order != null && order.equals("DESC")) {
            Collections.sort(users, (u1, u2) -> Integer.compare(u2.getScore(), u1.getScore()));
        }

        return users;
    }
}
