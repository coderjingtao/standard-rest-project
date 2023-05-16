package com.joseph.standardwebproject.controller;

import com.joseph.standardwebproject.common.response.CommonResponse;
import com.joseph.standardwebproject.entity.Course;
import com.joseph.standardwebproject.service.CourseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class CourseController {

    private final CourseService courseService;

    @GetMapping("/courses")
    public List<Course> getAllCourses(){
        return courseService.findAllCourses();
    }
    @GetMapping("/courses/{name}")
    public List<Course> getCoursesByName(@PathVariable String name){
        log.debug("name = "+name);
        return courseService.findByCourseName(name);
    }
    @PostMapping("/courses")
    public CommonResponse<Course> createCourse(@Valid @RequestBody Course course){
        log.debug("new course = {}", course);
        Course savedCourse = courseService.createCourse(course);
        return new CommonResponse<>(savedCourse);
    }
}
