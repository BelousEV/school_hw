package ru.hogwarts.school.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hogwarts.school.model.Faculty;

import java.util.Collection;


public interface FacultyRepository extends JpaRepository<Faculty, Long> {


    Collection<Faculty> findByNameOrColorIgnoreCase (String name, String color);
    //Добавить эндпоинт для поиска факультета по имени или цвету, игнорируя регистр,
    // т.е в GET-запросе будет передана строка, по которой будет происходить фильтрация.



    Collection<Faculty> findByColor(String color);

}
