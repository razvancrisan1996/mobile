package com.example.razvan.fitness;

/**
 * Created by Razvan on 11/19/2017.
 */

public class User {
    private String emailAddress, password, name;
    private int weight, age;
    private Genre genre;

    public enum Genre{
        MALE, FEMALE
    }

    public User(String emailAddress, String password, String name, int weight, int age, Genre genre) {
        this.emailAddress = emailAddress;
        this.password = password;
        this.name = name;
        this.weight = weight;
        this.age = age;
        this.genre = genre;
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
