package com.example.LMS.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.example.LMS.model.Lesson;
import org.springframework.stereotype.Repository;

import com.example.LMS.model.Course;

@Repository
public class CourseRepo {
    private final Map<Long, Course> courses = new HashMap<>();
    private final Map<Long,List<Lesson>>lessons = new HashMap<>();

    public Course save(Course course) {
        courses.put(course.getId(), course);
        return course;
    }

    public Optional<Course> findById(Long id) {
        return Optional.ofNullable(courses.get(id));
    }

    public List<Course> findAll() {
        return new ArrayList<>(courses.values());
    }
    public void deleteById(Long id) {
        courses.remove(id);
    }

    public void updateCourse(Course course) {
        courses.put(course.getId(), course);
    }

    public Lesson addLesson(Long courseId, Lesson lesson){
        List<Lesson> lessonList= lessons.getOrDefault(lesson.getCourseId(), new ArrayList<>());
        lessonList.add(lesson);
        Course course = courses.get(lesson.getCourseId());
        course.Lessons.add(lesson);
        lessons.put(lesson.getCourseId(), lessonList);
        return lesson;
    }

}

