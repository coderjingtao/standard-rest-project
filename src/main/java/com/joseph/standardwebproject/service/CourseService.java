package com.joseph.standardwebproject.service;

import com.joseph.standardwebproject.entity.Course;
import com.joseph.standardwebproject.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;

    private final JdbcTemplate jdbcTemplate;

    public List<Course> findAllCourses(){
        return courseRepository.findAll();
    }

    public List<Course> findByCourseName(String name){
        String sql = "select * from course where name like concat('%',?,'%')";
        return jdbcTemplate.query(sql,new BeanPropertyRowMapper<>(Course.class),name);
    }

    public Course createCourse(Course course){
        return courseRepository.save(course);
    }
}
