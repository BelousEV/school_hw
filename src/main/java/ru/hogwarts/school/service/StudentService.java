package ru.hogwarts.school.service;

import ru.hogwarts.school.model.Student;

import java.util.ArrayList;
import java.util.Collection;

public interface StudentService {

//    Student addStudent(Student student);
    Student createStudent(Student student);

    Student findStudent(long id);

    Student editStudent(Student student);

    void deleteStudent(long id);

    public Collection<Student> findByAge(int age);
}
