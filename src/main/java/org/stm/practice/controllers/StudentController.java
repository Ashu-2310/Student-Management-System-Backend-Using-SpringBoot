package org.stm.practice.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.stm.practice.entities.Student;
import org.stm.practice.exceptions.NotFoundException;
import org.stm.practice.service.StudentService;

import java.util.Map;

@RestController
@RequestMapping("student")
public class StudentController {

    private final StudentService studentService;

//    we can also write like:
//    @Autowired
//    private StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService){
        this.studentService = studentService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addStudent(@RequestBody @Valid Student student){
        try {
            return ResponseEntity.ok(studentService.addStudent(student));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(Map.of("error  ", e.getMessage()));
        }
    }

    @GetMapping("/{studentId}")
    public ResponseEntity<?> getStudentById(@PathVariable Long studentId){
        try {
            Student student = studentService.getStudentById(studentId);
            return ResponseEntity.ok(student);
//            return new ResponseEntity<>(student, HttpStatus.OK);
        } catch (NotFoundException e){
            return new ResponseEntity<>(Map.of("message", e.getMessage()), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(Map.of("error", e.getMessage()));
        }
    }

    @DeleteMapping("/{studentId}")
    public void deleteStudentById(@PathVariable(name = "studentId") Long studentId){
        studentService.deleteById(studentId);
    }

    @PutMapping("/{studentId}")
    public ResponseEntity<?> updateStudent(@PathVariable Long studentId, @RequestBody @Valid Student student){
        try {
            if (studentId != student.getId()) {
                return ResponseEntity.badRequest().body(Map.of("message", "The ID in the path and the body are not same"));
            }
            Student updatedStudent = studentService.updateStudent(student);
            return ResponseEntity.ok(updatedStudent);
        }
        catch (NotFoundException e){
            return new ResponseEntity<>(Map.of("message", e.getMessage()), HttpStatus.NOT_FOUND);
        }
        catch (Exception e) {
            return ResponseEntity.internalServerError().body(Map.of("error", e.getMessage()));
        }

    }
}
