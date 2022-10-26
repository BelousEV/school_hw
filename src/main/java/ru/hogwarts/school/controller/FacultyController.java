package ru.hogwarts.school.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.service.FacultyServiceImpl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Objects;

import static ru.hogwarts.school.service.FacultyServiceImpl.faculties;

@RestController
@RequestMapping("faculties")
public class FacultyController {

    @Autowired
    private FacultyServiceImpl facultyServiceImpl;

    public FacultyController(FacultyServiceImpl facultyServiceImpl) {
        this.facultyServiceImpl = facultyServiceImpl;
    }
    @GetMapping("{id}") //http://localhost:8081/faculties/23

    public ResponseEntity <Faculty> getFacultyInfo(@PathVariable long id) {
        Faculty faculty = FacultyServiceImpl.findFaculty(id);
        if (faculty == null) {
            ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(faculty);

    }



    @PostMapping // POST http://localhost:8081/faculties
    public Faculty createFaculty(@RequestBody Faculty faculty) {
        return FacultyServiceImpl.createFaculty(faculty);
    }
    @PutMapping //http://localhost:8081/faculties
    public ResponseEntity <Faculty> editFaculty(@RequestBody Faculty faculty){
        Faculty foundFaculty = FacultyServiceImpl.editFaculty(faculty);
        if (foundFaculty == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok (foundFaculty);
    }

    @DeleteMapping ("{id}") //удаление по айди http://localhost:8081/faculties/23

    public Faculty deleteFaculty(@PathVariable long id) {

        return FacultyServiceImpl.deleteFaculty(id);
    }

    //1909

    // Controller
    @GetMapping
    public ResponseEntity<Collection<Faculty>> findFaculties(@RequestParam(required = false) String color) {
        if (color != null && !color.isBlank()) {
            return ResponseEntity.ok(facultyServiceImpl.findByColor(color));
        }
        return ResponseEntity.ok(Collections.emptyList());
    }



}

