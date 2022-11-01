package com.jameswright722.workoutplanner.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
public class Workout {
    @Getter @Setter
    private Integer id;
    @Getter @Setter
    private String workout;
    @Getter @Setter
    private String weekday;
    @Getter @Setter
    private String split;

    public Workout(Integer id, String workout, String weekday, String split) {
        this.id = id;
        this.workout = workout;
        this.weekday = weekday;
        this.split = split;
    }

    public Workout(String workout, String weekday, String split) {
        this.workout = workout;
        this.weekday = weekday;
        this.split = split;
    }
}
