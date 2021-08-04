package com.vin.tutorial.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired

    public StudentService(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents(){
        return studentRepository.findAll();
    }

    public void addNewStudent(Student student) {
        Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());
        if(studentOptional.isPresent()){
            throw new IllegalStateException("Email Taken");
        }
        studentRepository.save(student);
    }

    public void deleteStudent(Long studentId){
        if (!studentRepository.existsById(studentId)){
            throw new IllegalStateException("Student with id " + studentId + " doesn't exists");
        }
        studentRepository.deleteById(studentId);
    }

    @Transactional
    public void updateStudent(Long studentId, String name, String email, String dob){
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalStateException("Student with id " + studentId + " doesn't exists"));
        if (name != null && name.length() > 0 && !student.getName().equals(name)){
            student.setName(name);
        }
        if (email != null && email.length() > 0 && !student.getEmail().equals(email)){
            student.setEmail(email);
        }

        LocalDate parseDob = LocalDate.parse(dob);

        if (!student.getDob().equals(parseDob)) {
            student.setDob(parseDob);
        }
    }
}
