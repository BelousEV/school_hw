package ru.hogwarts.school.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositories.StudentRepository;

import java.util.List;
import java.util.stream.Stream;

@Service
public class InfoService {
    private static final Logger LOG = LoggerFactory.getLogger(InfoService.class);
    private final StudentRepository studentRepository;


    public InfoService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public void testParallelStream(){
        StopWatch stopWatch = new StopWatch();
        stopWatch.start("plain stream");
        long sum = Stream.iterate(1L, a -> a +1)
                .limit(1_000_000L)
                .reduce(0L, (a, b) -> a + b );
        stopWatch.stop();
        LOG.info("Calculated value is {}; {}", sum, stopWatch.prettyPrint());
        stopWatch.start("parallel stream");
         sum = Stream.iterate(1L, a -> a +1)
                .limit(1_000_000L)
                .parallel()
                .reduce(0L, (a, b) -> a + b );
        stopWatch.stop();
        LOG.info("Calculated value is {}; {}", sum, stopWatch.prettyPrint());


    }
    //4.6
    public void printStudents() {
        List<Student> students = studentRepository.findAll(PageRequest.of(0,6)).getContent();
        printStudents(students.subList(0,2));
        new Thread(()-> printStudents(students.subList(2,4))).start();
        new Thread(()-> printStudents(students.subList(4,6))).start();
    }

    private void printStudents(List<Student> students){
        for (Student student: students){
            LOG.info(student.getName());
        }
    }
    private synchronized void printStudentsSync(List<Student> students){
        for (Student student: students){
            LOG.info(student.getName());
        }
    }

    public void printStudentsSync() {
        List<Student> students = studentRepository.findAll(PageRequest.of(0,6)).getContent();
        printStudentsSync(students.subList(0,2));
        new Thread(()-> printStudentsSync(students.subList(2,4))).start();
        new Thread(()-> printStudentsSync(students.subList(4,6))).start();
    }


//    int sum = Stream.iterate(1, a -› a +1) .
//            limit(1_000_000) .parallel()
//            .reduce(0, (a, b) -› a + b );
}
