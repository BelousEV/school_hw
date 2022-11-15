package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repositories.FacultyRepository;

import java.util.*;



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

    public Collection <Faculty> findByNameOrColorIgnoreCase (String name,String color) { //для БД
        return facultyRepository.findByNameOrColorIgnoreCase(name, color);
    }


}
