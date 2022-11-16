package ru.hogwarts.school.service;

import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

import java.util.Collection;
import java.util.Optional;

public interface FacultyService {
    Optional<Faculty> findFaculty(Long id);

    Faculty editFaculty(Faculty faculty);

    Faculty createFaculty(Faculty faculty);

    void deleteFaculty(Long id);

    Collection<Faculty> findByColor(String color);
    Collection<Faculty> getAll();

    Collection<Faculty> findByNameContainingOrColorContaining(String nameOrColor);

    Collection<Student> findStudentsFromFacultyById(Long id);
}
