package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

import java.util.*;

import static ru.hogwarts.school.service.StudentServiceImpl.students;

@Service
public class FacultyServiceImpl {
    public static final Map<Long, Faculty> faculties = new HashMap<>();

    private static long lastId = 10;

    public static Faculty createFaculty(Faculty faculty) {
        faculty.setId(++lastId); //айди первого факультета 11
        faculties.put(faculty.getId(), faculty);
        return faculty;
    }

    public static Faculty findFaculty(long id) {

        return faculties.get(id);
    }
    public static Faculty editFaculty(Faculty faculty) {
        if (faculties.containsKey(faculty.getId())) {
            faculties.put(faculty.getId(), faculty);
            return faculty;
        }
        return null;
    }

    public static Faculty deleteFaculty(long id) {

        return faculties.remove(id);
    }
    // Service
    public Collection<Student> findByAge(int age) {
        ArrayList<Student> result = new ArrayList< >();
        for (Student student : students.values()) {
            if (student.getAge() == age) {
                result.add(student);
            }
        }
        return result;
    }
    // Service
    public Collection<Faculty> findByColor(String color) {
        ArrayList<Faculty> result = new ArrayList<>();
        for (Faculty faculty : faculties.values()) {
            if (Objects.equals(faculty.getColor(), color)) {
                result.add(faculty);
            }
        }
        return result;
    }

}
