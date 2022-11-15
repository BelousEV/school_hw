package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositories.StudentRepository;

import java.util.Collection;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {

        this.studentRepository = studentRepository;
    }

    public Student createStudent(Student student) {

        return studentRepository.save(student);
    }

    public Optional<Student> findStudent(Long id) {
        return studentRepository.findById(id);
    }

    public Student editStudent(Student student) {
        return studentRepository.save(student);
    }

    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

    public Collection<Student> getAll() {
        return studentRepository.findAll();
    }

    // Service
    public Collection<Student> findByAge(Long age) {
        return studentRepository.findByAge(age);
    }
    public Collection<Student> findByAgeBetween(Long age_min, Long age_max) { //для БД
        return studentRepository.findByAgeBetween(age_min, age_max);
    }

    public Collection<Student> findByFacultyId(Long facultyId) {
        return studentRepository.findByFacultyId(facultyId);
    }

}
