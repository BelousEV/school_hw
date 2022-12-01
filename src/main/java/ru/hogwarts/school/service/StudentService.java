package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Avatar;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
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

    Long getMeanAge();
    Long getStudentsCount();
    List<Student> getLastFiveStudents();
}
