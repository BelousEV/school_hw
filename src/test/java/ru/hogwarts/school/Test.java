package ru.hogwarts.school;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import ru.hogwarts.school.controller.StudentController;
import ru.hogwarts.school.model.Student;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class HogwartsAppApplicationTests {
    @LocalServerPort
    private Long port;
    @Autowired
    private StudentController studentController;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void contextLoads() throws Exception {
        assertThat(studentController).isNotNull();
    }
    private String getUrlHogwarts(String str) {
        if (str.isEmpty() || str.isBlank())
            str = "";
        return "http://localhost:" + port.toString() + "/hogwarts/" + str;
//        return "http://localhost:8081/hogwarts/" + str;
    }
    private String getUrlStudents(String str) {
        if (str.isEmpty() || str.isBlank())
            str = "";
//        String answer = "http://localhost:" + port.toString() + "/students/" + str;
//        System.out.println(answer);
//        return answer;
        return "http://localhost:8081/students/" + str;
    }
    @Test
    public void testFaultMessage() throws Exception {
        assertThat(this.restTemplate.getForObject(getUrlHogwarts(""), String.class))
                .contains("WebApp is working");
    }
    @Test
    public void testFindStudentByAgeRange() throws Exception {
        assertThat(this.restTemplate.getForObject(getUrlStudents("findRange/?ageMin=15&ageMax=20"), String.class))
                .contains("Name15");
    }

    @Test
    public void testFindStudentById () throws Exception {
        assertThat(this.restTemplate.getForObject(getUrlStudents("14"), String.class))
                .contains("Name14");
    }
    @Test
    public void testFindStudentByAge () throws Exception {
        assertThat(this.restTemplate.getForObject(getUrlStudents("/age/17"), String.class))
                .contains("Name15");
    }

    @Test
    public void testFindFacultyOfStudent () throws Exception {
        assertThat(this.restTemplate.getForObject(getUrlStudents("/getFacultyFromStudentById/13"), String.class))
                .contains("Kogtevran");
    }

    @Test
    public void testAddStudent () throws Exception {
        Student student = new Student();
        student.setName("Somebody");
        student.setAge(100L);

        assertThat(this.restTemplate.postForObject(getUrlStudents(""), student, String.class))
                .isNotNull();
    }
    @Test
    public void testEditStudent () throws Exception {

        Student editStudent = new Student();
        editStudent.setId(1L);
        editStudent.setName("Another Name");
        editStudent.setAge(40L);

        this.restTemplate.put("http://localhost:" + port + "/students", editStudent, String.class);
        assertThat(editStudent.getName()).isEqualTo("Another Name");

    }

    @Test
    public void testDeleteStudent () throws Exception {
        this.restTemplate.delete("http://localhost:" + port + "/students/7", String.class);
        assertThat(studentController.getStudent(7L)).isEqualTo(ResponseEntity.notFound().build());
    }








}