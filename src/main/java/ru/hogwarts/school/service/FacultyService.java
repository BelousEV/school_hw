package ru.hogwarts.school.service;

import ru.hogwarts.school.model.Faculty;

import java.util.*;

public interface FacultyService {

//    Faculty addFaculty(Faculty faculty);

    Optional<Faculty> findFaculty(Long id);

    Faculty editFaculty(Faculty faculty);

    Faculty createFaculty(Faculty faculty);

    void deleteFaculty(Long id);

    public Collection<Faculty> findByColor(String color);
    public Collection<Faculty> getAll();

    Collection<Faculty> findByNameOrColorIgnoreCase(String name, String color);
}
