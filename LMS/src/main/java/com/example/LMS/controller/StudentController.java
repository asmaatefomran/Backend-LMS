package com.example.LMS.controller;

import com.example.LMS.model.Student;
import com.example.LMS.model.User;
import com.example.LMS.service.StudentService;
import com.example.LMS.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/student")
public class
StudentController {

    @Autowired
    private StudentService studentService;
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> registerStudent(@RequestBody Student student) {
        User registeredUser = userService.register(student);
        if (registeredUser != null) {
            return ResponseEntity.ok(registeredUser);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/login")
    public ResponseEntity<User> loginStudent(@RequestParam String email, @RequestParam String password) {
        // Call the login method in UserService
        User loggedInUser = userService.login(email, password).orElse(null);

        if (loggedInUser != null) {
            return ResponseEntity.ok(loggedInUser);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/enroll")
    public ResponseEntity<String> enroll(@RequestParam String StudId, @RequestParam long Couid) {

        // Call the enrollInCourse method from the StudentService
        String result = studentService.EnrollInCourse(StudId, Couid);

        // Return the response
        return ResponseEntity.ok(result);
    }

    @PostMapping("/Unenroll")
    public ResponseEntity<String> Unenroll(@RequestParam Long StudId, @RequestParam Long Couid) {
        // Convert Long IDs to String if required by the service method
        String studentId = StudId.toString();
        long courseId = Couid;

        // Call the enrollInCourse method from the StudentService
        String result = studentService.UnEnrollFromCourse(studentId,courseId);

        // Return the response
        return ResponseEntity.ok(result);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllStudents() {
        // Filter users with the role "student"
        List<User> students = userService.getAllUsers().stream()
                .filter(user -> "student".equalsIgnoreCase(user.getRole()))
                .toList(); // Collect only students

        return ResponseEntity.ok(students);
    }

    @PostMapping("/update")
    public ResponseEntity<Optional<User>> updateStudent(@RequestBody Student stu){
    Optional<User> updatedUser = studentService.updateStudent(stu);
        if (updatedUser != null) {
            return ResponseEntity.ok(updatedUser);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}