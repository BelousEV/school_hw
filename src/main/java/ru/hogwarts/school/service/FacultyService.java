package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

import java.util.HashMap;
import java.util.Map;

@Service
public class FacultyService {
    private static final Map<Long, Faculty> faculties = new HashMap<>();

    private static long lastId = 10;

    public static Faculty createFaculty(Faculty faculty) {
        faculty.setId(++lastId); //айди первого факультета 11
        faculties.put(lastId, faculty);
        return faculty;
    }

    public static Faculty findFaculty(long id) {
        return faculties.get(id);
    }
    public static Faculty editFaculty(Faculty faculty) {
        faculties.put(faculty.getId(), faculty);
        return faculty;
    }

    public static Faculty deleteFaculty(long id) {
        return faculties.remove(id);
    }

}
