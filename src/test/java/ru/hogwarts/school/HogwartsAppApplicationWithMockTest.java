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
import ru.hogwarts.school.controller.FacultyController;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repositories.FacultyRepository;
import ru.hogwarts.school.service.FacultyService;
import ru.hogwarts.school.service.FacultyServiceImpl;

import java.util.Collection;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = FacultyController.class)
//@SpringBootTest
//@AutoConfigureMockMvc //need this in Spring Boot test
public class HogwartsAppApplicationWithMockTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private FacultyRepository facultyRepository;
    @MockBean
    private FacultyServiceImpl facultyServiceImpl;
    @SpyBean
    private FacultyService facultyService;
    @InjectMocks
    private FacultyController facultyController;

    @Test
    public void saveAndGetFacultyTest() throws Exception {
        final Long id = 1L;
        final String name = "SomeName";
        final String color = "SomeColor";

        JSONObject facultyObject = new JSONObject();
        facultyObject.put("id", id);
        facultyObject.put("name", name);
        facultyObject.put("color", color);
        Faculty faculty = new Faculty();
        faculty.setId(id);
        faculty.setName(name);
        faculty.setColor(color);

        when(facultyRepository.save(any(Faculty.class))).thenReturn(faculty);
        when(facultyRepository.findById(any(Long.class))).thenReturn(Optional.of(faculty));

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/faculties/")
                        .content(facultyObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.color").value(color));
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/faculties/" + id)
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.color").value(color));
    }

    @Test
    public void getFacultyByNameOrColor() throws Exception {
        Long id = 1L;
        String name = "SomeFaculty";
        String color = "SomeColor";

        Faculty faculty = new Faculty();
        faculty.setId(id);
        faculty.setName(name);
        faculty.setColor(color);

        JSONObject facultyObject = new JSONObject();
        facultyObject.put("id", id);
        facultyObject.put("name", name);
        facultyObject.put("color", color);


        Collection<Faculty> collectionFaculty = null;
        collectionFaculty.add(new Faculty(id, name, color));
        collectionFaculty.add(new Faculty(id+1, name, color));
        collectionFaculty.add(new Faculty(id+2, name, color));



        when(facultyRepository.save(any(Faculty.class))).thenReturn(faculty);
        when(facultyRepository.findByNameIgnoreCaseContainingOrColorIgnoreCaseContaining(color, color))
                .thenReturn(collectionFaculty);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/faculties/")
                        .content(facultyObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.color").value(color));
        mockMvc.perform(MockMvcRequestBuilders.get("/faculties?nameOrColor=" + color)
                        .accept((MediaType.APPLICATION_JSON)))
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.color").value(color));
    }

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
//    public void getFacultysOfFaculty() throws Exception {
//        Long id = 1L;
//        String name = "SomeFaculty";
//        String color = "SomeColor";
//
//        Faculty faculty = new Faculty();
//        faculty.setId(1L);
//        faculty.setName(name);
//        faculty.setColor(color);
//
//        Faculty faculty = new Faculty(1L, "Peter", 50L, faculty, null);
//        Collection<Faculty> facultys = new ArrayList<>();
//        facultys.add(faculty);
////        faculty.setFacultys(facultys);
//
//        JSONObject j = new JSONObject();
//        j.put("id", id);
//        j.put("name", "Peter");
//        j.put("age", 50);
//
//        when(facultyRepository.save(any(Faculty.class))).thenReturn(faculty);
//        when(facultyRepository.findById(any(Long.class))).thenReturn(Optional.of(faculty));
//
//        mockMvc.perform(MockMvcRequestBuilders.get("/faculties/1/facultys")
//                        .content(j.toString())
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .accept(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$[0].id").value(id))
//                .andExpect(jsonPath("$[0].name").value("Peter"))
//                .andExpect(jsonPath("$[0].age").value(50));
//    }

}