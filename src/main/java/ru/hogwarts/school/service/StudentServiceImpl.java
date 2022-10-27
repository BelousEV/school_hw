package ru.hogwarts.school.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositories.StudentRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class StudentServiceImpl {

    private  final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public  Student createStudent(Student student) {
         return studentRepository.save(student);
    }

    public  Student findStudent(long id) {
        return studentRepository.getById(id);
    }

    public  Student editStudent(Student student) {
        return studentRepository.save(student);
    }

    public  void deleteStudent(long id) {
         studentRepository.deleteById(id);
    }

    public Collection<Student> getAllStudent(){
        return studentRepository.findAll();
    }

    // Service
//    public Collection<Student> findByAge(int age) {
//        ArrayList<Student> result = new ArrayList< >();
//        for (Student student : students.values()) {
//            if (student.getAge() == age) {
//                result.add(student);
//            }
//        }
//        return result;
    }

