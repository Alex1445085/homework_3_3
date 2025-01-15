package hogwartsschoolhw33;

import hogwartsschoolhw33.controller.FacultyController;
import hogwartsschoolhw33.model.Faculty;
import hogwartsschoolhw33.model.Student;
import hogwartsschoolhw33.repository.FacultyRepository;
import hogwartsschoolhw33.repository.StudentRepository;
import hogwartsschoolhw33.service.FacultyService;
import hogwartsschoolhw33.service.StudentService;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import java.util.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = FacultyController.class)
public class FacultyControllerWebMvcTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private FacultyRepository facultyRepository;
    @MockBean
    private StudentRepository studentRepository;
    @MockBean
    private StudentService studentService;
    @SpyBean
    private FacultyService facultyService;

    @Test
    public void saveFaculty() throws Exception {
        long id = 1L;
        String name = "nameOne";
        String color = "colorRed";

        JSONObject facultyObject = new JSONObject();
        facultyObject.put("name", name);
        facultyObject.put("color", color);

        Set<Faculty> facultyList = new HashSet<>();

        Faculty faculty = new Faculty();
        faculty.setId(id);
        faculty.setName(name);
        faculty.setColor(color);

        facultyList.add(faculty);

        Collection<Faculty> faculties = List.of(faculty);
        Student s1 = new Student("Aa", 12);
        Student s2 = new Student("Bb", 12);
        s1.setId(1L);
        s2.setId(2L);
        Collection<Student> students = List.of(s1, s2);

        when(facultyRepository.save(any(Faculty.class))).thenReturn(faculty);
        when(facultyRepository.findById(any(Long.class))).thenReturn(Optional.of(faculty));
        when(facultyRepository.findByColorIgnoreCase(any(String.class))).thenReturn(facultyList);
        when(studentRepository.findAllByFaculty_id(any(Long.class))).thenReturn(students);
        when(facultyService.studentsByIdOfFaculty(any(Long.class))).thenReturn(students);

        mockMvc.perform(MockMvcRequestBuilders
                .post("/faculty")
                .content(facultyObject.toString())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(faculty.getId()))
                .andExpect(jsonPath("$.name").value(faculty.getName()))
                .andExpect(jsonPath("$.color").value(faculty.getColor()));

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/faculty/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/faculty/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(faculty.getId()))
                .andExpect(jsonPath("$.name").value(faculty.getName()))
                .andExpect(jsonPath("$.color").value(faculty.getColor()));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/faculty?color=" + faculty.getColor())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(faculty.getId()))
                .andExpect(jsonPath("$[0].name").value(faculty.getName()))
                .andExpect(jsonPath("$[0].color").value(faculty.getColor()));



        mockMvc.perform(MockMvcRequestBuilders
                        .get("/faculty/getStudentsByIdOfFaculty/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(s1.getId()))
                .andExpect(jsonPath("$[0].name").value(s1.getName()))
                .andExpect(jsonPath("$[0].age").value(s1.getAge()));
    }
}