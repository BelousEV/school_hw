package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositories.FacultyRepository;

import java.util.*;



@Service
public class FacultyServiceImpl {



    private final FacultyRepository facultyRepository;

    public FacultyServiceImpl(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public  Faculty createFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    public  Faculty findFaculty(long id) {
        return facultyRepository.getById(id);
            }
    public  Faculty editFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    public  void  deleteFaculty(long id) {
        facultyRepository.deleteById(id);
    }
    public Collection<Faculty> getAllFaculties(){
        return facultyRepository.findAll();
    }
    // Service


 //       public Collection<Faculty> findByColor(String color) {
 //            ArrayList<Faculty> result = new ArrayList<>();
 //            for (Faculty faculty : faculties.values()) {
 //                if (Objects.equals(faculty.getColor(), color)) {
 //
    //                   result.add(faculty);
    //            }
    //        }
    //        return result;
    //    }

}
