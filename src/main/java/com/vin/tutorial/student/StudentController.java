package com.vin.tutorial.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@RestController // Define an object as a controller
@RequestMapping(path="api/v1/student") // Define an object as request mapping
public class StudentController {

    private final StudentService studentService;

    @Autowired // link an object with other object
    public StudentController(StudentService studentService){
        this.studentService = studentService;
    }

    @GetMapping // Create a get function on object
    public List<Student> getStudents(){
        return studentService.getStudents();
    }

    @PostMapping // Create a post function on object with json as an argument when post
    public void registerNewStudent(@RequestBody Student student){
        studentService.addNewStudent(student);
    }

    @DeleteMapping(path = "{studentId}") // Create a delete function with path as an argument to this function
    public void deleteStudent(@PathVariable("studentId") Long studentId){
        studentService.deleteStudent(studentId);
    }

    @PutMapping(path = "{studentId}") // Create a put function to update data on web , with path as an argument (StudentID) and parameter name and email
    public void updateStudent(
                @PathVariable("studentId") Long studentId,
                @RequestParam(required = false) String name,
                @RequestParam(required = false) String email,
                @RequestParam(required = false) String dob
            )
    {
        studentService.updateStudent(studentId,name,email,dob);

    }
}
