package ru.hogwarts.school.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;


@RestController
@RequestMapping("students")

public class StudentController {


    private final StudentService studentService;

    public StudentController(StudentService studentService) {

        this.studentService = studentService;
    }

    @GetMapping("{id}") //GET http://localhost:8081/students/id
    public ResponseEntity<Student> getStudent(@PathVariable Long id) {
        Optional<Student> student = studentService.findStudent(id);
        if (student.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(student.get());

    }

    @PostMapping  // POST http://localhost:8081/students
    public Student createStudent(@RequestBody Student student) {

        return studentService.createStudent(student);
    }

    @PutMapping  //PUT редактирование http://localhost:8081/students
    public ResponseEntity<Student> editStudent(@RequestBody Student student) {
        Student foundStudent = studentService.editStudent(student);
        if (foundStudent == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(foundStudent);
    }

    @DeleteMapping("{id}") //DELETE http://localhost:8081/students/23
    public ResponseEntity<Long> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.ok().build();
    }


    // Controller
    @GetMapping("/age/{age}")
    public ResponseEntity<Collection<Student>> findStudents(@PathVariable Long age) {
        if (age > 0) {
            return ResponseEntity.ok(studentService.findByAge(age));
        }
        return ResponseEntity.ok(Collections.emptyList());
    }

    @GetMapping // для SQL БД
    public ResponseEntity<Collection<Student>> findStudent(@RequestParam Long ageMin, @RequestParam Long ageMax) {
        Collection<Student> answer = studentService.findByAgeBetween(ageMin, ageMax);
        if (answer.isEmpty()) {
            return ResponseEntity.ok(Collections.emptyList());
        }
        return ResponseEntity.ok(answer);
    }

    @GetMapping("/getFacultyFromStudentById")
    public ResponseEntity<Faculty> findFacultyFromStudentById(@RequestParam Long id) {
        Optional<Student> studentOptional = studentService.findStudent(id);
        if (studentOptional.isEmpty())
            return ResponseEntity.notFound().build();

        Optional<Faculty> optionalFaculty = studentOptional.get().getFaculty();
        if (optionalFaculty.isEmpty())
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(optionalFaculty.get());
    }
}
