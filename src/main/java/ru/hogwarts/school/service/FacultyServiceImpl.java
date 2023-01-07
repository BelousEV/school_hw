package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositories.FacultyRepository;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

@Service
public class FacultyServiceImpl implements FacultyService {
    private static final Logger LOG = LoggerFactory.getLogger(FacultyServiceImpl.class);
    private final FacultyRepository facultyRepository;

    public FacultyServiceImpl(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }
    public  Faculty createFaculty(Faculty faculty) {
        LOG.debug("Method createFaculty was invoked");
        return facultyRepository.save(faculty);
    }
    public Optional<Faculty> findFaculty(Long id) {
        LOG.debug("Method findFaculty was invoked");
        return facultyRepository.findById(id);
    }
    public  Faculty editFaculty(Faculty faculty) {
        LOG.debug("Method editFaculty was invoked");
        return facultyRepository.save(faculty);
    }
    public  void  deleteFaculty(Long id) {
        LOG.debug("Method deleteFaculty was invoked");
        facultyRepository.deleteById(id);
    }
    public Collection<Faculty> getAll(){
        LOG.debug("Method getAll was invoked");
        return facultyRepository.findAll();
    }

    // Service

    public Collection<Faculty> findByColor(String color) {
        LOG.debug("Method findByColor was invoked");
        return facultyRepository.findByColor(color);
    }

    public Collection<Faculty> findByNameContainingOrColorContaining(String nameOrColor) { //для БД
        LOG.debug("Method findByNameContainingOrColorContaining was invoked");
        Collection<Faculty> answer = facultyRepository.findByNameIgnoreCaseContainingOrColorIgnoreCaseContaining(nameOrColor, nameOrColor);
        if (answer.isEmpty())
            return Collections.emptyList();
        return answer;
    }

    public Collection<Student> findStudentsFromFacultyById(Long id) {
        LOG.debug("Method findStudentsFromFacultyById was invoked");
        Optional<Faculty> answer = findFaculty(id);
        if (answer.isEmpty())
            return Collections.emptyList();
        return answer.get().getStudents();
    }

    //4.5


    public String findTheLongestFacultyName(){
        return facultyRepository.findAll()
                .stream()
                .map(Faculty::getName)
                .max((f1, f2) -> f1.length() - f2.length())
                .orElseThrow(null);
    }

}
