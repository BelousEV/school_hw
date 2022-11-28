package ru.hogwarts.school;

import net.minidev.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.hogwarts.school.controller.StudentController;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositories.StudentRepository;
import ru.hogwarts.school.service.StudentService;
import ru.hogwarts.school.service.StudentServiceImpl;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = StudentController.class)
//@SpringBootTest
//@AutoConfigureMockMvc //need this in Spring Boot test
public class HogwartsAppApplicationWithMockTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private StudentRepository studentRepository;
    @MockBean
    private StudentServiceImpl studentServiceImpl;
    @SpyBean
    private StudentService studentService;
    @InjectMocks
    private StudentController studentController;

    @Test
    public void saveStudentTest() throws Exception {
        final Long id = 1L;
        final String name = "SomeName";
        final Long age = 44L;

        JSONObject studentObject = new JSONObject();
        studentObject.put("id", id);
        studentObject.put("name", name);
        studentObject.put("age", age);
        Student student = new Student(id, name, age, null, null);

        when(studentRepository.save(any(Student.class))).thenReturn(student);
        when(studentRepository.findById(any(Long.class))).thenReturn(Optional.of(student));

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/students/")
                        .content(studentObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.age").value(age));
//        mockMvc.perform(MockMvcRequestBuilders
//                        .get("/students/" + id)
//                        .accept(MediaType.APPLICATION_JSON)
//                )
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.id").value(id))
//                .andExpect(jsonPath("$.name").value(name))
//                .andExpect(jsonPath("$.age").value(age));
    }
//    @Test
//    public void saveFacultyTest() throws Exception {
//        Long id = 1L;
//        String name = "SomeFaculty";
//        String color = "SomeColor";
//
//        JSONObject facultyObject = new JSONObject();
//
//        facultyObject.put("name", name);
//        facultyObject.put("color", color);
//        Faculty faculty = new Faculty();
//        faculty.setId(id);
//        faculty.setName(name);
//        faculty.setColor(color);
//
//        when(facultyRepository.save(any(Faculty.class))).thenReturn(faculty);
//        when(facultyRepository.findById(any(Long.class))).thenReturn(Optional.of(faculty));
////        Collection<Faculty> faculties = null;
////        when(facultyRepository.findByNameIgnoreCaseContainingOrColorIgnoreCaseContaining(color, color))
////                .thenReturn(faculties);
//
//        mockMvc.perform(MockMvcRequestBuilders
//                        .post("/faculties")
//                        .content(facultyObject.toString())
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.id").value(id))
//                .andExpect(jsonPath("$.name").value(name))
//                .andExpect(jsonPath("$.color").value(color));
//
//
//    }

//    @Test
//    public void getFacultyById() throws Exception {
//        Long id = 1L;
//        String name = "SomeFaculty";
//        String color = "SomeColor";
//
//        JSONObject facultyObject = new JSONObject();
//
//        facultyObject.put("name", name);
//        facultyObject.put("color", color);
//        Faculty faculty = new Faculty();
//        faculty.setId(id);
//        faculty.setName(name);
//        faculty.setColor(color);
//
//        when(facultyRepository.save(any(Faculty.class))).thenReturn(faculty);
//        when(facultyRepository.findById(any(Long.class))).thenReturn(Optional.of(faculty));
//
//        mockMvc.perform(MockMvcRequestBuilders.get("/faculties/1")
//                        .accept(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.id").value(id))
//                .andExpect(jsonPath("$.name").value(name))
//                .andExpect(jsonPath("$.color").value(color));
//    }
//
//    @Test
//    public void getFacultyByNameOrColor() throws Exception {
//        Long id = 1L;
//        String name = "SomeFaculty";
//        String color = "SomeColor";
//
//        JSONObject facultyObject = new JSONObject();
//
//        facultyObject.put("name", name);
//        facultyObject.put("color", color);
//        Faculty faculty = new Faculty();
//        faculty.setId(id);
//        faculty.setName(name);
//        faculty.setColor(color);
//
////        when(facultyRepository.findById(any(Long.class))).thenReturn(Optional.of(faculty));
//        when(facultyRepository.findByNameIgnoreCaseContainingOrColorIgnoreCaseContaining(color, color))
//                .thenReturn(faculty);
//
//        mockMvc.perform(MockMvcRequestBuilders.get("/faculties?nameOrColor=" + color)
//                        .accept((MediaType.APPLICATION_JSON)))
//                .andExpect(jsonPath("$.id").value(id))
//                .andExpect(jsonPath("$.name").value(name))
//                .andExpect(jsonPath("$.color").value(color));
//    }
//
//    @Test
//    public void editFacultyTest() throws Exception {
//        Long id = 1L;
//        String name = "SomeFaculty";
//        String color = "SomeColor";
//
//        JSONObject facultyObject = new JSONObject();
//        facultyObject.put("id", id);
//        facultyObject.put("name", name);
//        facultyObject.put("color", color);
//        Faculty faculty = new Faculty();
//        faculty.setId(id);
//        faculty.setName(name);
//        faculty.setColor(color);
//
//        when(facultyRepository.save(any(Faculty.class))).thenReturn(faculty);
//        when(facultyRepository.findById(any(Long.class))).thenReturn(Optional.of(faculty));
//
//        mockMvc.perform(MockMvcRequestBuilders.put("/faculties")
//                        .content(facultyObject.toString())
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .accept(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.id").value(id))
//                .andExpect(jsonPath("$.name").value(name))
//                .andExpect(jsonPath("$.color").value(color));
//    }
//
//    @Test
//    public void deleteFacultyTest() throws Exception {
//        Long id = 1L;
//        String name = "SomeFaculty";
//        String color = "SomeColor";
//
//        JSONObject facultyObject = new JSONObject();
//        facultyObject.put("name", name);
//        facultyObject.put("color", color);
//        Faculty faculty = new Faculty();
//        faculty.setId(id);
//        faculty.setName(name);
//        faculty.setColor(color);
//
//        when(facultyRepository.save(any(Faculty.class))).thenReturn(faculty);
//        when(facultyRepository.findById(any(Long.class))).thenReturn(Optional.of(faculty));
//
//        mockMvc.perform(MockMvcRequestBuilders.delete("/faculties/" + id)
//                        .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    public void getStudentsOfFaculty() throws Exception {
//        Long id = 1L;
//        String name = "SomeFaculty";
//        String color = "SomeColor";
//
//        Faculty faculty = new Faculty();
//        faculty.setId(1L);
//        faculty.setName(name);
//        faculty.setColor(color);
//
//        Student student = new Student(1L, "Peter", 50L, faculty, null);
//        Collection<Student> students = new ArrayList<>();
//        students.add(student);
////        faculty.setStudents(students);
//
//        JSONObject j = new JSONObject();
//        j.put("id", id);
//        j.put("name", "Peter");
//        j.put("age", 50);
//
//        when(facultyRepository.save(any(Faculty.class))).thenReturn(faculty);
//        when(facultyRepository.findById(any(Long.class))).thenReturn(Optional.of(faculty));
//
//        mockMvc.perform(MockMvcRequestBuilders.get("/faculties/1/students")
//                        .content(j.toString())
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .accept(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$[0].id").value(id))
//                .andExpect(jsonPath("$[0].name").value("Peter"))
//                .andExpect(jsonPath("$[0].age").value(50));
//    }

}