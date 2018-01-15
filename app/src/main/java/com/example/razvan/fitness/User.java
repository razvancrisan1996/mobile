package com.example.razvan.fitness;

import java.util.List;

/**
 * Created by Razvan on 11/19/2017.
 */

public class User {
    private int id;
    private String emailAddress, password, name;
    private int weight, age;
    private Genre genre;
    private UType uType;
    private List<Workout> workouts;

    public enum Genre{
        MALE, FEMALE
    }

    public enum UType{
        ADMIN, USER
    }
    public User(int id,String emailAddress, String password, String name, int weight, int age, Genre genre, List<Workout> workouts) {
        this.id = id;
        this.emailAddress = emailAddress;
        this.password = password;
        this.name = name;
        this.weight = weight;
        this.age = age;
        this.genre = genre;
        this.workouts = workouts;
    }

    public User(String emailAddress,int age,int weight){
        this.emailAddress = emailAddress;
        this.age = age;
        this.weight = weight;
    }
    public User(int id,String emailAddress,int age,int weight){
        this.id = id;
        this.emailAddress = emailAddress;
        this.age = age;
        this.weight = weight;
    }

    public UType getuType() {
        return uType;
    }

    public void setuType(UType uType) {
        this.uType = uType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Workout> getWorkouts() {
        return workouts;
    }

    public void setWorkouts(List<Workout> workouts) {
        this.workouts = workouts;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (!emailAddress.equals(user.emailAddress)) return false;
        return password.equals(user.password);
    }

    @Override
    public int hashCode() {
        int result = emailAddress.hashCode();
        result = 31 * result + password.hashCode();
        return result;
    }
}
