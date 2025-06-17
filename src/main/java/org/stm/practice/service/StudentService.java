package org.stm.practice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.stm.practice.entities.Student;
import org.stm.practice.repositories.StudentRepository;

import java.util.Optional;

//service contains business logic
@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }

    public Student addStudent(Student student){
        return this.studentRepository.save(student);
    }

    public Student getStudentById(Long studentId){
        Optional<Student> found = studentRepository.findById(studentId);
        return found.orElse(null);
    }

    public void deleteById(Long studentId){
        studentRepository.deleteById(studentId);
    }

    public Student updateStudent(Student student){
        Student exists = getStudentById(student.getId());
        //student does not exist
        if(exists == null){
            return null;
        }
        exists.setFirstName(student.getFirstName());
        exists.setLastName(student.getLastName());
        return studentRepository.save(exists);
    }


}
