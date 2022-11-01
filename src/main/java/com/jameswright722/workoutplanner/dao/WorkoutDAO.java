package com.jameswright722.workoutplanner.dao;

import com.jameswright722.workoutplanner.model.Workout;

import java.util.List;

public interface WorkoutDAO {
    public int save(Workout workout);

    public int update(Workout workout);

    public Workout get(Integer id);
    public int delete(Integer id);
    public List<Workout> list();

}
