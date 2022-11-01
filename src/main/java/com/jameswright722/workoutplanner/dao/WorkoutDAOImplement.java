package com.jameswright722.workoutplanner.dao;

import com.jameswright722.workoutplanner.model.Workout;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class WorkoutDAOImplement implements WorkoutDAO {

    private JdbcTemplate jdbcTemplate;
    public WorkoutDAOImplement(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public int save(Workout w) {
        String sql = "INSERT INTO workoutdb.workout(workout, weekday, split) VALUES (?,?,?)";
        return jdbcTemplate.update(sql, w.getWorkout(), w.getWeekday(), w.getSplit());
    }

    @Override
    public int update(Workout w) {
        String sql = "UPDATE workoutdb.workout SET workout=?, weekday=?, split=? WHERE workout_id=?";
        return jdbcTemplate.update(sql, w.getWorkout(), w.getWeekday(), w.getSplit(), w.getId());
    }

    @Override
    public Workout get(Integer id) {
        String sql = "SELECT * FROM workoutdb.workout WHERE workout_id=" + id;

        ResultSetExtractor<Workout> extractor = new ResultSetExtractor<Workout>() {
            @Override
            public Workout extractData(ResultSet rs) throws SQLException, DataAccessException {
                if(rs.next()) {
                    String workout = rs.getString("workout");
                    String weekday = rs.getString("weekday");
                    String split = rs.getString("split");

                    return new Workout(id, workout, weekday, split);
                }
                return null;
            }
        };
        return jdbcTemplate.query(sql, extractor);
    }

    @Override
    public int delete(Integer id) {
        String sql = "DELETE FROM workoutdb.workout WHERE workout_id=" + id;
        return jdbcTemplate.update(sql);
    }

    @Override
    public List<Workout> list() {
        String sql = "SELECT * FROM workoutdb.workout";

        RowMapper<Workout> rowMapper = new RowMapper<Workout>() {
            @Override
            public Workout mapRow(ResultSet rs, int rowNum) throws SQLException {
                Integer id = rs.getInt("workout_id");
                String workout = rs.getString("workout");
                String weekday = rs.getString("weekday");
                String split = rs.getString("split");

                return new Workout(id, workout, weekday, split);
            }
        };
        return jdbcTemplate.query(sql, rowMapper);
    }


}
