package hogwartsschoolhw33;

import hogwartsschoolhw33.controller.FacultyController;
import hogwartsschoolhw33.model.Faculty;
import hogwartsschoolhw33.model.Student;
import hogwartsschoolhw33.repository.FacultyRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;

@Nested
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class FacultyControllerRestTemplateTest {

    @LocalServerPort
    private int port;
    @Autowired
    private FacultyRepository facultyRepository;
    @Autowired
    private FacultyController facultyController;
    @Autowired
    private TestRestTemplate restTemplate;

    Faculty f1 = new Faculty("F1", "red");
    Faculty f2 = new Faculty("F2", "green");
    Faculty f3 = new Faculty("F3", "blue");

    @BeforeEach
    public void init() {
        facultyRepository.save(f1);
        facultyRepository.save(f2);
        facultyRepository.save(f3);
    }

    @AfterEach
    public void reInit() {
        facultyRepository.deleteAll();
    }


    @Test
    public void contextLoad() {
        Assertions.assertThat(facultyController).isNotNull();
    }

    @Test
    public void findFaculty() {
        ResponseEntity<Faculty> actual = restTemplate.
                exchange("http://localhost:" + port + "/faculty/" + f2.getId(),
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<Faculty>() {
                        });

        assertThat(actual).isNotNull();
        assertThat(actual.getStatusCode()).isEqualTo(HttpStatus.OK);
        Faculty actualFaculty = actual.getBody();
        assertThat(actualFaculty).isEqualTo(f2);
    }

    @Test
    public void deleteFaculty() {
        ResponseEntity<String> actual = restTemplate
                .exchange("http://localhost:" + port + "/faculty/" + f1.getId(),
                        HttpMethod.DELETE,
                        null,
                        String.class);
        assertThat(actual).isNotNull();
        assertThat(actual.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void addFaculty() throws Exception {
        Assertions.assertThat(this.restTemplate
                .postForObject("http://localhost:" + port + "/faculty/", f1, String.class)
                .contains("F1"));
    }

    @Test
    public void facultyByColor() {
        ResponseEntity<Collection<Faculty>> actual = restTemplate.
                exchange("http://localhost:" + port + "/faculty?color=" + f1.getColor(),
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<Collection<Faculty>>() {
                        });

        assertThat(actual).isNotNull();
        assertThat(actual.getStatusCode()).isEqualTo(HttpStatus.OK);
        Collection<Faculty> actualFaculty = actual.getBody();
        assertThat(actualFaculty).contains(f1);
    }

    @Test
    public void findByNameOrColorIgnoreCase() {
        ResponseEntity<Collection<Faculty>> actual = restTemplate.
                exchange("http://localhost:" + port +
                                "/faculty/findByNameOrColor/%7Bname%7D/%7Bcolor%7D?color=" + f1.getColor(),
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<Collection<Faculty>>() {
                        });
        assertThat(actual).isNotNull();
        assertThat(actual.getStatusCode()).isEqualTo(HttpStatus.OK);
        Collection<Faculty> actualFaculty = actual.getBody();
        assertThat(actualFaculty).contains(f1);
    }
}

