package com.example.razvan.fitness;

/**
 * Created by Razvan on 11/9/2017.
 */

public class Workout {
    private int id;
    private String description;
    private int duration;
    private Level difficulty;

    public enum Level {
        LOW, LOW_MODERATE, MODERATE, MODERATE_HIGH, HIGH
    }

    public Workout(String description, int duration, Level difficulty) {
        this.description = description;
        this.duration = duration;
        this.difficulty = difficulty;
    }

    public Workout(int id, String description, int duration, Level difficulty) {
        this.id = id;
        this.description = description;
        this.duration = duration;
        this.difficulty = difficulty;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Level getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Level difficulty) {
        this.difficulty = difficulty;
    }

    @Override
    public String toString() {
        return "Workout{" +
                "description='" + description + '\'' +
                ", duration=" + duration +
                ", difficulty=" + difficulty +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Workout workout6 = (Workout) o;

        if (id != workout6.id) return false;
        if (duration != workout6.duration) return false;
        if (!description.equals(workout6.description)) return false;
        return difficulty == workout6.difficulty;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + description.hashCode();
        result = 31 * result + duration;
        result = 31 * result + difficulty.hashCode();
        return result;
    }
}
