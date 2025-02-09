package hogwartsschoolhw33;

import hogwartsschoolhw33.controller.StudentController;
import hogwartsschoolhw33.model.Student;
import hogwartsschoolhw33.repository.FacultyRepository;
import hogwartsschoolhw33.repository.StudentRepository;
import hogwartsschoolhw33.service.FacultyService;
import hogwartsschoolhw33.service.StudentService;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
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

import org.junit.jupiter.api.Test;

@WebMvcTest(controllers = StudentController.class)
public class StudentControllerWebMvcTest {
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

    Student student = new Student();
    Collection<Student> studentList = List.of();
    JSONObject studentObject = new JSONObject();
    Student s1 = new Student("Aa", 10);
    Student s2 = new Student("Bb", 10);

    @BeforeEach
    public void init() throws JSONException {
        Long id = 2L;
        String name = "Aa";
        int age = 12;
        studentObject.put("name", name);
        studentObject.put("age", age);
        student.setId(id);
        student.setName(name);
        student.setAge(age);
        s1.setId(1L);
        s2.setId(2L);
        studentList = List.of(s1, s2);
    }

    @Test
    public void saveStudent() throws Exception {
        when(studentRepository.save(any(Student.class))).thenReturn(student);
        when(studentService.addStudent(any(Student.class))).thenReturn(student);
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/student")
                        .content(studentObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(student.getId()))
                .andExpect(jsonPath("$.name").value(student.getName()))
                .andExpect(jsonPath("$.age").value(student.getAge()));
    }

    @Test
    public void deleteStudent() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/student/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void findStudent() throws Exception {
        when(studentRepository.findById(any(Long.class))).thenReturn(Optional.of(student));
        when(studentService.findStudent(any(Long.class))).thenReturn(student);
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/student/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(student.getId()))
                .andExpect(jsonPath("$.name").value(student.getName()))
                .andExpect(jsonPath("$.age").value(student.getAge()));
    }

    @Test
    public void findStudentsByAge() throws Exception {
//        when(studentRepository.findByAge(any(int.class))).thenReturn((Set<Student>) studentList);
        when(studentService.studentsByAge(any(int.class))).thenReturn(studentList);
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/student?age=" + student.getAge())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$[0].id").value(s1.getId()))
                .andExpect(jsonPath("$[0].name").value(s1.getName()))
                .andExpect(jsonPath("$[0].age").value(s1.getAge()));
    }

    @Test
    public void findAll() throws Exception {
//        when(studentRepository.findAll()).thenReturn((List<Student>) studentList);
        when(studentService.allStudents()).thenReturn(studentList);
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/student/allStudents")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(s1.getId()))
                .andExpect(jsonPath("$[0].name").value(s1.getName()))
                .andExpect(jsonPath("$[0].age").value(s1.getAge()));
    }

    @Test
    public void studentsBetweenAge() throws Exception {
//        when(studentRepository.findByAgeBetween(any(int.class), any(int.class))).thenReturn((Set<Student>) studentList);
        when(studentService.findByAgeBetween(any(int.class), any(int.class))).thenReturn(studentList);
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/student/studentsBetweenAge?min=" + s1.getAge() + "&max=" + (s1.getAge() + 1))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[1].id").value(s2.getId()))
                .andExpect(jsonPath("$[1].name").value(s2.getName()))
                .andExpect(jsonPath("$[1].age").value(s2.getAge()));
    }
}
