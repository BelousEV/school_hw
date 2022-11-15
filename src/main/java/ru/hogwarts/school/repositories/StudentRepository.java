package ru.hogwarts.school.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.hogwarts.school.model.Student;

import java.util.Collection;

public interface StudentRepository extends JpaRepository <Student, Long>{

    Student findByName(String name);

    Collection <Student> findByAgeBetween(Long age_min, Long age_max); // Добавить эндпоинт для получения всех студентов,
    // возраст которых находится в промежутке, пришедшем в запросе, т.е в GET-запросе
    // будут передаваться два числа (min и max). Для этого в репозитории следует создать
    // метод findByAgeBetween().

    Collection<Student> findByAge(Long age);

    Collection<Student> findByFacultyId(Long facultyId);

}
