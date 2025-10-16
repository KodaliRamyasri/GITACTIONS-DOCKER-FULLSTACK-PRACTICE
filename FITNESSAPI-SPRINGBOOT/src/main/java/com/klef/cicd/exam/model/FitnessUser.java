package com.klef.cicd.exam.model;

import jakarta.persistence.*;

@Entity
@Table(name = "fitness_user")
public class FitnessUser {

    @Id
    @Column(name = "user_id")
    private int id;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "email", nullable = false, unique = true, length = 50)
    private String email;

    @Column(name = "age", nullable = false)
    private int age;

    @Column(name = "gender", nullable = false, length = 10)
    private String gender;

    @Column(name = "workout_type", nullable = false, length = 50)
    private String workoutType;

    @Column(name = "duration", nullable = false)
    private int duration;

    @Column(name = "calories_burned", nullable = false)
    private double caloriesBurned;

    @Column(name = "intensity", length = 20)
    private String intensity;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public String getWorkoutType() {
        return workoutType;
    }
    public void setWorkoutType(String workoutType) {
        this.workoutType = workoutType;
    }
    public int getDuration() {
        return duration;
    }
    public void setDuration(int duration) {
        this.duration = duration;
    }
    public double getCaloriesBurned() {
        return caloriesBurned;
    }
    public void setCaloriesBurned(double caloriesBurned) {
        this.caloriesBurned = caloriesBurned;
    }
    public String getIntensity() {
        return intensity;
    }
    public void setIntensity(String intensity) {
        this.intensity = intensity;
    }

    @Override
    public String toString() {
        return "FitnessUser [id=" + id + ", name=" + name + ", email=" + email + ", age=" + age
                + ", gender=" + gender + ", workoutType=" + workoutType + ", duration=" + duration
                + ", caloriesBurned=" + caloriesBurned + ", intensity=" + intensity + "]";
    }
}
