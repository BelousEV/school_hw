package ru.hogwarts.school.service;

import ru.hogwarts.school.model.Student;

import java.util.Collection;
import java.util.Optional;

public interface StudentService {

//    Student addStudent(Student student);
    Student createStudent(Student student);

    Optional<Student> findStudent(Long id);

    Student editStudent(Student student);

    void deleteStudent(Long id);

    public Collection<Student> findByAge(Long age);
    public Collection<Student> getAll();

    Collection <Student>findByAgeBetween(Long age_min, Long age_max); // добавила для запросов БД

//    Collection<Student> findAllByFacultyId(Long facultyId);
}
