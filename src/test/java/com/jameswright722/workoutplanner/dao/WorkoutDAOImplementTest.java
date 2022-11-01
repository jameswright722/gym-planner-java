package com.jameswright722.workoutplanner.dao;


import com.jameswright722.workoutplanner.model.Workout;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class WorkoutDAOImplementTest {
    private DriverManagerDataSource dataSource;
    private WorkoutDAOImplement dao;

    @BeforeEach
    void setUpBeforeEach() {
        dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/workoutdb");
        dataSource.setUsername("root");
        dataSource.setPassword("password");

        dao = new WorkoutDAOImplement(dataSource);
    }

    @Test
    void save() {
        Workout workout = new Workout("Squat", "Monday", "10/10/10");
        int result = dao.save(workout);

        assertTrue(result > 0);
    }

    @Test
    void update() {
        Workout workout = new Workout(2,"Squat", "Monday", "12/12/12");
        int result = dao.update(workout);
        assertTrue(result > 0);
    }

    @Test
    void get() {
        Integer id = 1;
        Workout workout = dao.get(id);
        if(workout != null) {
            System.out.println(workout);
        }

        assertNotNull(workout);
    }

    @Test
    void delete() {
        Integer id = 1;
        int result = dao.delete(id);

        assertTrue(result > 0);
    }

    @Test
    void list() {
        List<Workout> workoutList = dao.list();

        for(Workout w : workoutList) {
            System.out.println(w);
        }

        assertTrue(workoutList.isEmpty());
    }
}