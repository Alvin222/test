package com.vin.tutorial.student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {

    //this function is to find student in the database
    @Query("Select s FROM Student s Where s.email = ?1")
    Optional<Student> findStudentByEmail(String email);
}
