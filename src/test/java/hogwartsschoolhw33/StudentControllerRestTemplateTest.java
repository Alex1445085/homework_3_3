package hogwartsschoolhw33;

import hogwartsschoolhw33.controller.StudentController;
import hogwartsschoolhw33.model.Student;
import hogwartsschoolhw33.repository.StudentRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import java.util.Collection;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StudentControllerRestTemplateTest {

    @LocalServerPort
    private int port;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private StudentController studentController;
    @Autowired
    private TestRestTemplate restTemplate;

    Student s1 = new Student("A1", 12);
    Student s2 = new Student("A2", 10);
    Student s3 = new Student("A3", 14);

    @BeforeEach
    public void init() {
        studentRepository.save(s1);
        studentRepository.save(s2);
        studentRepository.save(s3);
    }

    @AfterEach
    public void reInit() {
        studentRepository.deleteAll();
    }


    @Test
    public void contextLoad() {
        Assertions.assertThat(studentController).isNotNull();
    }

    @Test
    public void findStudent() {
        ResponseEntity<Student> actual = restTemplate.
                exchange("http://localhost:" + port + "/student/" + s2.getId(),
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<Student>() {
                        });

        assertThat(actual).isNotNull();
        assertThat(actual.getStatusCode()).isEqualTo(HttpStatus.OK);
        Student actualStudent = actual.getBody();
        assertThat(actualStudent).isEqualTo(s2);
    }

    @Test
    public void deleteStudent() {
        ResponseEntity<String> actual = restTemplate
                .exchange("http://localhost:" + port + "/student/" + s1.getId(),
                        HttpMethod.DELETE,
                        null,
                        String.class);
        assertThat(actual).isNotNull();
        assertThat(actual.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void addStudent() throws Exception {
        Assertions.assertThat(this.restTemplate
                .postForObject("http://localhost:" + port + "/student/", s1, String.class)
                .contains("A1"));
    }

    @Test
    public void allStudents() {
        ResponseEntity<Collection<Student>> actual = restTemplate.
                exchange("http://localhost:" + port + "/student/allStudents",
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<Collection<Student>>() {
                        });

        assertThat(actual).isNotNull();
        assertThat(actual.getStatusCode()).isEqualTo(HttpStatus.OK);
        Collection<Student> actualStudent = actual.getBody();
        assertThat(actualStudent).containsExactlyInAnyOrder(s1, s2, s3);
    }

    @Test
    public void findByAgeBetween() {
        ResponseEntity<Collection<Student>> actual = restTemplate.
                exchange("http://localhost:" + port + "/student/studentsBetweenAge?min=" +
                                (s1.getAge() - 1) + "&max=" + (s1.getAge() + 1),
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<Collection<Student>>() {
                        });

        assertThat(actual).isNotNull();
        assertThat(actual.getStatusCode()).isEqualTo(HttpStatus.OK);
        Collection<Student> actualStudent = actual.getBody();
        assertThat(actualStudent).contains(s1);
    }
}
