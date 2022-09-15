package ru.hogwarts.school.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

@RestController
@RequestMapping("students")

public class StudentController {

    @Autowired
    private StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("{id}") //GET http://localhost:8081/students/23
    public ResponseEntity getStudentInfo(@PathVariable long id) {
        Student student = StudentService.findStudent(id);
        if (student == null) {
            ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(student);

    }

    @PostMapping  // POST http://localhost:8081/students
    public Student createStudent(@RequestBody Student student) {

        return StudentService.createStudent(student);
    }

    @PutMapping  //редактирование
    public Student editStudent(@RequestBody Student student) {
      return StudentService.editStudent(student);
    }

    @DeleteMapping ("{id}")

    public Student deleteStudent(@PathVariable long id) {
        return StudentService.deleteStudent(id);
    }
}
