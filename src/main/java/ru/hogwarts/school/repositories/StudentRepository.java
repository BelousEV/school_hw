package ru.hogwarts.school.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.hogwarts.school.model.Student;

import java.lang.reflect.Array;
import java.util.Collection;

public interface StudentRepository extends JpaRepository <Student, Long>{

    Collection<Student> findByAge(Long age);

}
