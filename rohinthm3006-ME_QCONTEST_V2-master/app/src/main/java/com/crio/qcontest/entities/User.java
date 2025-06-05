package com.crio.qcontest.entities;

public class User {
    private final Long id;
    private final String name;
    private Integer score;

    public User(String name) {
        this.id = null; // Assigned later by the repository
        this.name = name;
        this.score = 1500; // Default score
    }

    public User(Long id, User other) {
        this.id = id;
        this.name = other.name;
        this.score = other.score;
    }

    // Add this constructor if explicit fields are needed
    public User(Long id, String name, Integer score) {
        this.id = id;
        this.name = name;
        this.score = score;
    }

    // Getters and setters remain unchanged
    public String getName() {
        return name;
    }

    public Integer getScore() {
        return score;
    }

    public Long getId() {
        return id;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", name=" + name + ", score=" + score + "]";
    }
}
