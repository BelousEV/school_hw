package ru.hogwarts.school.service;

import ru.hogwarts.school.model.Student;

import java.util.ArrayList;
import java.util.Collection;

public interface StudentService {

//    Student addStudent(Student student);
    Student createStudent(Student student);

    Student findStudent(Long id);

    Student editStudent(Student student);

    void deleteStudent(Long id);

    public Collection<Student> findByAge(Long age);
}
