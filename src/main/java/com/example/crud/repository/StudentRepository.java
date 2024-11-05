package com.example.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.crud.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    // Find student by email
    Student findByEmail(String email);
}

