package ru.hogwarts.school.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.FacultyService;
import ru.hogwarts.school.service.StudentService;

@RestController
@RequestMapping("faculties")
public class FacultyController {

    @Autowired
    private FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }
    @GetMapping("{id}") //http://localhost:8081/faculties/23

    public ResponseEntity getFacultyInfo(@PathVariable long id) {
        Faculty faculty = FacultyService.findFaculty(id);
        if (faculty == null) {
            ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(faculty);

    }



    @PostMapping // POST http://localhost:8081/faculties
    public Faculty createFaculty(@RequestBody Faculty faculty) {
        return FacultyService.createFaculty(faculty);
    }
    @PutMapping //http://localhost:8081/faculties
    public Faculty editFaculty(@RequestBody Faculty faculty) {
        return FacultyService.editFaculty(faculty);
    }

    @DeleteMapping ("{id}") //удаление по айди http://localhost:8081/faculties/23

    public Faculty deleteFaculty(@PathVariable long id) {
        return FacultyService.deleteFaculty(id);
    }

}
