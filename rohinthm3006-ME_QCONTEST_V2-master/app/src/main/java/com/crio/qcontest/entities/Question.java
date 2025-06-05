package com.crio.qcontest.entities;

public class Question {
    private Long id;  // Remove 'final' to allow setting it after creation
    private final String text;
    private final DifficultyLevel level;
    private final Integer score;

    public Question(String text, DifficultyLevel level, Integer score) {
        this.id = null;  // Initially, the id is null and will be set later
        this.text = text;
        this.level = level;
        this.score = score;
    }

    public Question(Long id, Question other) {
        this.id = id;  // Now you can assign the id from another question
        this.text = other.text;
        this.level = other.level;
        this.score = other.score;
    }

    public String getName() {
        return text;
    }

    public DifficultyLevel getLevel() {
        return level;
    }

    public Integer getScore() {
        return score;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {  // Add setter for id to allow modification later
        this.id = id;
    }

    @Override
    public String toString() {
        return "Question{name='" + text + "', level=" + level + ", score=" + score + ", id=" + id + "}";
    }
}
