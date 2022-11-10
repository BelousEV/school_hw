package ru.hogwarts.school.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.FacultyService;
import ru.hogwarts.school.service.FacultyServiceImpl;
import ru.hogwarts.school.service.StudentServiceImpl;

import java.util.*;


@RestController
@RequestMapping("faculties")
public class FacultyController {


    private FacultyService facultyService;

    public FacultyController(FacultyService initFacultyService) {
         facultyService = initFacultyService;
    }
    @GetMapping("{id}") //http://localhost:8081/faculties/23
    public ResponseEntity <Faculty> getFaculty(@PathVariable Long id) {
        Faculty faculty = facultyService.findFaculty(id);
        if (faculty == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(faculty);

    }
    @PostMapping // POST http://localhost:8081/faculties
    public Faculty createFaculty(@RequestBody Faculty faculty) {
        return facultyService.createFaculty(faculty);
    }
    @PutMapping //http://localhost:8081/faculties
    public ResponseEntity <Faculty> editFaculty(@RequestBody Faculty faculty){
        Faculty foundFaculty = facultyService.editFaculty(faculty);
        if (foundFaculty == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok (foundFaculty);
    }

    @DeleteMapping ("{id}") //удаление по айди http://localhost:8081/faculties/23
    public ResponseEntity<?> deleteFaculty(@PathVariable Long id) {
        facultyService.deleteFaculty(id);
        return ResponseEntity.ok().build();
    }

    //1909

    // Controller
    @GetMapping("/color/{color}")
    public ResponseEntity<Collection<Faculty>> findFaculties(@PathVariable String color) {
        if (color != null && !color.isBlank()) {
            return ResponseEntity.ok(facultyService.findByColor(color));
        }
        return ResponseEntity.ok(Collections.emptyList());
    }

    @GetMapping
    public ResponseEntity<Collection<Faculty>> getAll() {
        return ResponseEntity.ok(facultyService.getAll());
    }

}

