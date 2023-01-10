package ru.hogwarts.school.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.hogwarts.school.model.Student;

import java.util.Collection;
import java.util.List;

public interface StudentRepository extends JpaRepository <Student, Long>{

    Collection <Student> findByAgeBetween(Long age_min, Long age_max);

    Collection<Student> findByAge(Long age);

    @Query(value = "SELECT COUNT(*) FROM student", nativeQuery = true)
    Long getAmountOfAllStudents();

    @Query(value = "SELECT AVG(age) FROM student", nativeQuery = true)
    Long getAverageAgeAllStudents();

    @Query(value = "SELECT * FROM student ORDER BY id DESC LIMIT 5", nativeQuery = true)
    List<Student> getLastFiveStudents();
}
