package com.example.razvan.fitness;

/**
 * Created by Razvan on 11/9/2017.
 */

public class Workout {
    private int idWorkout;
    private int idUser;
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



    public Workout(int idUser, String description, int duration, Level difficulty) {
        this.idUser = idUser;
        this.description = description;
        this.duration = duration;
        this.difficulty = difficulty;
    }
    public Workout(int idWorkout,int idUser, String description, int duration, Level difficulty) {
        this.idWorkout = idWorkout;
        this.idUser = idUser;
        this.description = description;
        this.duration = duration;
        this.difficulty = difficulty;
    }
    public int getIdWorkout() {
        return idWorkout;
    }

    public void setIdWorkout(int idWorkout) {
        this.idWorkout = idWorkout;
    }

    public int getId() {
        return idUser;
    }

    public void setId(int id) {
        this.idUser = id;
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

        if (idUser != workout6.idUser) return false;
        if (duration != workout6.duration) return false;
        if (!description.equals(workout6.description)) return false;
        return difficulty == workout6.difficulty;
    }

    @Override
    public int hashCode() {
        int result = idUser;
        result = 31 * result + description.hashCode();
        result = 31 * result + duration;
        result = 31 * result + difficulty.hashCode();
        return result;
    }
}
