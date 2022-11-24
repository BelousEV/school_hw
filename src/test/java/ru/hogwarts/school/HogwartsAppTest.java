package ru.hogwarts.school;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;
import ru.hogwarts.school.model.Student;

import java.net.URI;
import java.util.Collection;
import java.util.Set;

@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HogwartsAppTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void test_create_student(){
        Student student = given_student_with("studentName", 25L);
        ResponseEntity<Student> response =
                when_sending_create_student_request(getUriBuilder().build().toUri(), student);
        then_student_has_been_created(response);
    }

    @Test
    public void test_get_student_by_id(){
        Student student = given_student_with("studentName", 25L);
        ResponseEntity<Student> createRespose =
                when_sending_create_student_request(getUriBuilder().build().toUri(), student);
        then_student_has_been_created(createRespose);

        Student createdStudent = createRespose.getBody();
        then_student_with_id_has_been_found(createdStudent.getId(), createdStudent);
    }

    @Test
    public  void test_findByAge(){
        Student student_18 = given_student_with("studentName3", 18L);
        Student student_25 = given_student_with("studentName1", 25L);
        Student student_28 = given_student_with("studentName2", 28L);
        Student student_32 = given_student_with("studentName4", 32L);

        when_sending_create_student_request(getUriBuilder().build().toUri(), student_18);
        when_sending_create_student_request(getUriBuilder().build().toUri(), student_25);
        when_sending_create_student_request(getUriBuilder().build().toUri(), student_28);
        when_sending_create_student_request(getUriBuilder().build().toUri(), student_32);

        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.add("age", "25");
        then_student_are_found_by_criteria(queryParams, student_25);
    }


    @Test
    public  void test_findByAgeBetween(){
        Student student_18 = given_student_with("studentName3", 18L);
        Student student_25 = given_student_with("studentName1", 25L);
        Student student_28 = given_student_with("studentName2", 28L);
        Student student_32 = given_student_with("studentName4", 32L);

        when_sending_create_student_request(getUriBuilder().build().toUri(), student_18);
        when_sending_create_student_request(getUriBuilder().build().toUri(), student_25);
        when_sending_create_student_request(getUriBuilder().build().toUri(), student_28);
        when_sending_create_student_request(getUriBuilder().build().toUri(), student_32);

        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.add("minAge", "20");
        queryParams.add("maxAge", "30");
        then_student_are_found_by_criteria(queryParams, student_25, student_28);
    }


    @Test
    public void test_update(){
        Student student = given_student_with("studentName", 25L);
        ResponseEntity<Student> responseEntity =
                when_sending_create_student_request(getUriBuilder().build().toUri(), student);
        then_student_has_been_created(responseEntity);
        Student createStudent = responseEntity.getBody();

        when_updating_student(createStudent, 32L, "newName");
        then_student_has_been_updated(createStudent, 32L, "newName");
    }




    @Test
    public void test_delete(){
        Student student = given_student_with("studentName", 25L);
        ResponseEntity<Student> responseEntity = when_sending_create_student_request(getUriBuilder().build().toUri(), student);
        then_student_has_been_created(responseEntity);
        Student createStudent = responseEntity.getBody();

        when_deleting_student(createStudent);
        then_student_not_found(createStudent);
    }

    private void when_deleting_student(Student createStudent) {
        restTemplate.delete(getUriBuilder().path("/{id}").buildAndExpand(createStudent.getId()).toUri());
    }
    private void then_student_not_found(Student createStudent) {
        URI uri = getUriBuilder().path("/id").buildAndExpand(createStudent.getId()).toUri();
        ResponseEntity<Student> emptyResp = restTemplate.getForEntity(uri, Student.class);

        Assertions.assertThat(emptyResp.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }


    private void then_student_has_been_updated(Student createStudent, Long newAge, String newName) {
        URI uri =getUriBuilder().cloneBuilder().path("/id").buildAndExpand(createStudent.getId()).toUri();
        ResponseEntity<Student> updateStudentsPs = restTemplate.getForEntity(uri, Student.class);

        Assertions.assertThat(updateStudentsPs.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(updateStudentsPs.getBody()).isNotNull();
        Assertions.assertThat(updateStudentsPs.getBody().getAge()).isEqualTo(newAge);
        Assertions.assertThat(updateStudentsPs.getBody().getName()).isEqualTo(newName);
    }


    private void when_updating_student(Student createStudent, Long newAge, String newName) {
        createStudent.setAge(newAge);
        createStudent.setName(newName);

        restTemplate.put(getUriBuilder().build().toUri(), createStudent);
    }

    private void then_student_are_found_by_criteria(MultiValueMap<String, String> queryParams, Student... students) {
        URI uri = getUriBuilder().queryParams(queryParams).build().toUri();

        ResponseEntity<Set<Student>> response = restTemplate.exchange(
                uri,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Set<Student>>(){
                });

        Assertions.assertThat(response.getBody()).isNotNull();
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        Set<Student> actualResult = response.getBody();
        resetIds(actualResult);
        Assertions.assertThat(actualResult).containsExactlyInAnyOrder(students);
    }

    private void resetIds(Collection<Student> students) { //сбрасываем айдишники метод then_student_are_found_by_criteria
        students.forEach(it -> it.setId(null));
    }


    private void then_student_with_id_has_been_found(Long studentId, Student student) {
        URI uri =getUriBuilder().path("/id").buildAndExpand(studentId).toUri();
        ResponseEntity<Student> response = restTemplate.getForEntity(uri, Student.class);
        Assertions.assertThat(response.getBody()).isEqualTo(student);
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    private void then_student_has_been_created(ResponseEntity<Student> response) {
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(response.getBody()).isNotNull();
        Assertions.assertThat(response.getBody().getId()).isNotNull();
    }

    private UriComponentsBuilder getUriBuilder() {
        return UriComponentsBuilder.newInstance()
                .scheme("http")
                .host("localhost")
                .port (port)
                .path("/hogwarts/students");
    }

    private ResponseEntity<Student> when_sending_create_student_request(URI uri, Student student) {
        return restTemplate.postForEntity(uri, student, Student.class);
    }

    private Student given_student_with(String name, Long age) {
        return new Student(name, age);
    }

}
