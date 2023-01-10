package ru.hogwarts.school.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.service.FacultyService;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;


@RestController
@RequestMapping("faculties")
public class FacultyController {
    private final FacultyService facultyService;

    public FacultyController(FacultyService initFacultyService) {
        facultyService = initFacultyService;
    }
    @GetMapping("{id}") //http://localhost:8081/faculties/23
    public ResponseEntity<Faculty> getFaculty(@PathVariable Long id) {
        Optional<Faculty> answer = facultyService.findFaculty(id);
        if (answer.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(answer.get());
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
    public ResponseEntity<Long> deleteFaculty(@PathVariable Long id) {
        facultyService.deleteFaculty(id);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<Collection<Faculty>> getAll() {
        return ResponseEntity.ok(facultyService.getAll());//вероятно, лишнее о_О
    }
    @GetMapping("/findByNameOrColor/{nameOrColor}") //http://localhost:8081/faculties/findByNameOrColor/{color}
    public ResponseEntity<Collection<Faculty>> findByNameContainingOrColorContaining(@PathVariable String nameOrColor) {
        if (nameOrColor.isBlank()) {
            return ResponseEntity.ok(Collections.emptyList());
        }
        return ResponseEntity.ok(facultyService.findByNameContainingOrColorContaining(nameOrColor));
    }

//    @GetMapping
//    public ResponseEntity<Collection<Student>> findStudentsByFacultyId(@RequestParam Long facultyId) {
//        return ResponseEntity.ok(facultyService.findStudentsByFacultyId(facultyId));
//    }

    //4.5


    @GetMapping("/findTheLongestFacultyName") //http://localhost:8081/faculties/findTheLongestFacultyName
    public String findTheLongestFacultyName() {
        return facultyService.findTheLongestFacultyName();
    }
}

