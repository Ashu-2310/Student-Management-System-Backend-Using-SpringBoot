package org.stm.practice.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.stm.practice.entities.Student;
import org.stm.practice.service.StudentService;

@RestController
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService){
        this.studentService = studentService;
    }

    @PostMapping("/student/add")
    public Student addStudent(@RequestBody @Valid Student student){
        return studentService.addStudent(student);
    }

    @GetMapping("/student/{studentId}")
    public Student getStudentById(@PathVariable Long studentId){
        return studentService.getStudentById(studentId);
    }

    @DeleteMapping("/student/{studentId}")
    public void deleteStudentById(@PathVariable(name = "studentId") Long studentId){
        studentService.deleteById(studentId);
    }

    @PutMapping("student/{studentId}")
    public Student updateStudent(@PathVariable Long studentId, @RequestBody Student student){
        if(studentId != student.getId()){

        }
        return studentService.updateStudent(student);
    }
}
