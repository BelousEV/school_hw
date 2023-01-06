package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Avatar;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositories.StudentRepository;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class StudentServiceImpl implements StudentService {
    private static final Logger LOG = LoggerFactory.getLogger(StudentServiceImpl.class);
    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }
    public Student createStudent(Student student) {
        LOG.debug("Method createStudent was invoked");
        return studentRepository.save(student);
    }
    public Optional<Student> findStudent(Long id) {
        LOG.debug("Method findStudent was invoked");
        return studentRepository.findById(id);
    }
    public Student editStudent(Student student) {
        LOG.debug("Method editStudent was invoked");
        return studentRepository.save(student);
    }
    public void deleteStudent(Long id) {
        LOG.debug("Method deleteStudent was invoked");
        studentRepository.deleteById(id);
    }
    public Collection<Student> getAll() {
        LOG.debug("Method getAll was invoked");
        return studentRepository.findAll();
    }
    public Long getStudentsCount() {
        LOG.debug("Method getStudentsCount was invoked");
        return studentRepository.getAmountOfAllStudents();
    }
    public Long getMeanAge() {
        LOG.debug("Method getMeanAge was invoked");
        return studentRepository.getAverageAgeAllStudents();
    }
    public List<Student> getLastFiveStudents() {
        LOG.debug("Method getLastFiveStudents was invoked");
        return studentRepository.getLastFiveStudents();
    }
    // Service
    public Collection<Student> findByAge(Long age) {
        LOG.debug("Method findByAge was invoked");
        return studentRepository.findByAge(age);
    }
    public Collection<Student> findByAgeBetween(Long ageMin, Long ageMax) {
        LOG.debug("Method findByAgeBetween was invoked"); //для БД
        return studentRepository.findByAgeBetween(ageMin, ageMax);
    }

    public Optional<Faculty> findFacultyFromStudentById(Long id) {
        LOG.debug("Method findFacultyFromStudentById was invoked");
        Optional<Student> studentOptional = findStudent(id);
        if (studentOptional.isEmpty())
            return Optional.empty();
        return studentOptional.get().getFaculty();
    }

    public Optional<Avatar> findAvatarFromStudentById(Long id) {
        LOG.debug("Method findAvatarFromStudentById was invoked");
        Optional<Student> studentOptional = findStudent(id);
        if (studentOptional.isEmpty())
            return Optional.empty();
        return studentOptional.get().getAvatar();
    }
}