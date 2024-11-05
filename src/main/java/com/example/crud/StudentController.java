package com.example.crud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.crud.model.Student;
import com.example.crud.repository.StudentRepository;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    // Create a new student
    @PostMapping("/add")
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        if (studentRepository.findByEmail(student.getEmail()) != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(null); // Conflict status if email already exists
        }
        Student savedStudent = studentRepository.save(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedStudent);
    }

    // Get all students
    @GetMapping("/")
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    // Get student by ID
    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
        return studentRepository.findById(id)
                .map(student -> ResponseEntity.ok(student))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }
}

