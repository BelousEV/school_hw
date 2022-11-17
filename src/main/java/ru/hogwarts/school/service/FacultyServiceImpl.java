package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositories.FacultyRepository;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

@Service
public class FacultyServiceImpl implements FacultyService {
    private final FacultyRepository facultyRepository;

    public FacultyServiceImpl(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public  Faculty createFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    public Optional<Faculty> findFaculty(Long id) {
        return facultyRepository.findById(id);
    }
    public  Faculty editFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    public  void  deleteFaculty(Long id) {
        facultyRepository.deleteById(id);
    }
    public Collection<Faculty> getAll(){
        return facultyRepository.findAll();
    }
    // Service


    public Collection<Faculty> findByColor(String color) {
        return facultyRepository.findByColor(color);
    }

    public Collection<Faculty> findByNameContainingOrColorContaining(String nameOrColor) { //для БД
        Collection<Faculty> answer = facultyRepository.findByNameIgnoreCaseContainingOrColorIgnoreCaseContaining(nameOrColor, nameOrColor);
        if (answer.isEmpty())
            return Collections.emptyList();
        return answer;
    }

    public Collection<Student> findStudentsFromFacultyById(Long id) {
        Optional<Faculty> answer = findFaculty(id);
        if (answer.isEmpty())
            return Collections.emptyList();
        return answer.get().getStudents();
    }

}
