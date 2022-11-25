package ru.hogwarts.school.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
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
    }
    private String getUrlStudents(String str) {
        if (str.isEmpty() || str.isBlank())
            str = "";
        return "http://localhost:" + port.toString() + "/hogwarts/students/" + str;
    }
    @Test
    public void testDefaultMessage() throws Exception {
        assertThat(this.restTemplate.getForObject(getUrlHogwarts(""), String.class))
                .contains("WebApp is working");
    }

    @Test
    public void testGetStudents() throws Exception {
        assertThat(this.restTemplate.getForObject(getUrlHogwarts(""), String.class))
                .isNotEmpty();
    }

    @Test
    public void testPostAndDeleteStudents() throws Exception {
        Student student = new Student("TestName", 55L);
        Student answer = this.restTemplate.postForObject(getUrlStudents(""), student, Student.class);
        assertThat(answer).isNotNull();
        Long answerId = answer.getId();
        this.restTemplate.delete(getUrlStudents(answerId.toString()));
        System.out.println(answerId);
        assertThat(studentController.getStudent(answerId)).isEqualTo(ResponseEntity.notFound().build());
    }
    @Test
    public void testFindStudentById () throws Exception {
        assertThat(this.restTemplate.getForObject(getUrlStudents("14"), String.class))
                .contains("Name14");
    }
    @Test
    public void testFindStudentByAge() throws Exception {
        assertThat(this.restTemplate.getForObject(getUrlStudents("age/25"), String.class))
                .contains("Name12");
    }
    @Test
    public void testFindStudentByAgeRange() throws Exception {
        assertThat(this.restTemplate.getForObject(getUrlStudents("findRange?ageMin=15&ageMax=20"), String.class))
                .contains("Name15");
    }
    @Test
    public void testFindFacultyOfStudent () throws Exception {
        assertThat(this.restTemplate.getForObject(getUrlStudents("/findFacultyFromStudentById/13"), String.class))
                .contains("Kogtevran");
    }

    @Test
    public void testEditStudent () throws Exception {
        Student answer = this.restTemplate.getForObject(getUrlStudents("14"), Student.class);
        assertThat(answer).isNotNull();

        String oldName = answer.getName();

        answer.setName("New Name");
        this.restTemplate.put(getUrlStudents(""), answer);

        Student answer2 = this.restTemplate.getForObject(getUrlStudents("14"), Student.class);
        assertThat(answer2).isNotNull();

        assertThat(answer2.getName()).isNotEqualTo(oldName);

        answer.setName(oldName);
        this.restTemplate.put(getUrlStudents(""), answer);
    }
}
