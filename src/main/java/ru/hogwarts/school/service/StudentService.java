package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;

import java.util.HashMap;
import java.util.Map;

@Service
public class StudentService {

    private static final Map<Long, Student> students = new HashMap<>();
    private static long lastId = 0;

    public static Student createStudent(Student student) {
        student.setId(++lastId); //айди первого студента 1
        students.put(lastId, student);
        return student;
    }

    public static Student findStudent(long id) {
        return students.get(id);
    }

    public static Student editStudent(Student student) {
        students.put(student.getId(), student);
        return student;
    }

    public static Student deleteStudent(long id) {
        return students.remove(id);
    }
}
