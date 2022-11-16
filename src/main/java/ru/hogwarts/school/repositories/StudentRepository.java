package ru.hogwarts.school.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.hogwarts.school.model.Student;

import java.util.Collection;

public interface StudentRepository extends JpaRepository <Student, Long>{

    Collection <Student> findByAgeBetween(Long age_min, Long age_max);

    Collection<Student> findByAge(Long age);

}
