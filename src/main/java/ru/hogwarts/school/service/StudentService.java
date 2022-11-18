package ru.hogwarts.school.service;

import ru.hogwarts.school.model.Avatar;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

import java.util.Collection;
import java.util.Optional;

public interface StudentService {

//    Student addStudent(Student student);
    Student createStudent(Student student);

    Optional<Student> findStudent(Long id);

    Student editStudent(Student student);

    void deleteStudent(Long id);

    Collection<Student> findByAge(Long age);
    Collection<Student> getAll();

    Collection<Student> findByAgeBetween(Long ageMin, Long ageMax); // добавила для запросов БД

    Optional<Faculty> findFacultyFromStudentById(Long id);

    Optional<Avatar> findAvatarFromStudentById(Long id);

}
