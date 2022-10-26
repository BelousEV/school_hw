package ru.hogwarts.school.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentServiceImpl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;



@RestController
@RequestMapping("students")

public class StudentController {

    @Autowired
    private StudentServiceImpl studentService;

    public StudentController(StudentServiceImpl studentService) {
        this.studentService = studentService;
    }

    @GetMapping("{id}") //GET http://localhost:8081/students/23
    public ResponseEntity <Student> getStudentInfo(@PathVariable long id) {
        Student student = StudentServiceImpl.findStudent(id);
        if (student == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(student);

    }

    @PostMapping  // POST http://localhost:8081/students
    public Student createStudent(@RequestBody Student student) {

        return StudentServiceImpl.createStudent(student);
    }

    @PutMapping  //PUT редактирование http://localhost:8081/students
    public ResponseEntity <Student> editStudent (@RequestBody Student student){
      Student foundStudent = StudentServiceImpl.editStudent(student);
        if (foundStudent == null) {
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok (foundStudent);
    }

    @DeleteMapping ("{id}") //DELETE http://localhost:8081/students/23

    public ResponseEntity deleteStudent(@PathVariable long id) {
        StudentServiceImpl.deleteStudent(id);
        return ResponseEntity.ok().build();
    }

    //1909

    // Controller
    @GetMapping
    public ResponseEntity<Collection<Student>> findStudents(@RequestParam(required = false) int age) {
        if (age > 0) {
            return ResponseEntity.ok(studentService.findByAge(age));
        }
        return ResponseEntity.ok(Collections.emptyList());
    }


}
