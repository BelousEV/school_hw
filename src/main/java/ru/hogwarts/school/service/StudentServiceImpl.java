package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Avatar;
import ru.hogwarts.school.model.Faculty;
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
    public Collection<Student> findByAgeBetween(Long ageMin, Long ageMax) { //для БД
        return studentRepository.findByAgeBetween(ageMin, ageMax);
    }

    public Optional<Faculty> findFacultyFromStudentById(Long id) {
        Optional<Student> studentOptional = findStudent(id);
        if (studentOptional.isEmpty())
            return Optional.empty();
        return studentOptional.get().getFaculty();
    }

    public Optional<Avatar> findAvatarFromStudentById(Long id) {
        Optional<Student> studentOptional = findStudent(id);
        if (studentOptional.isEmpty())
            return Optional.empty();
        return studentOptional.get().getAvatar();
    }
}