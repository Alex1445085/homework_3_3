package hogwartsschoolhw33.controller;

import hogwartsschoolhw33.model.Faculty;
import hogwartsschoolhw33.model.Student;
import hogwartsschoolhw33.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/student")
public class StudentController {

    public final StudentService servStudent;

    public StudentController(StudentService servStudent) {
        this.servStudent = servStudent;
    }

    @GetMapping("{id}")
    public Student findStudent(@PathVariable Long id) {
        Student student = servStudent.findStudent(id);
        return student;
    }

    @PostMapping
    public Student addStudent(@RequestBody Student student) {
        return servStudent.addStudent(student);
    }

    @PutMapping
    public ResponseEntity<Student> editStudent(@RequestBody Student student) {
        Student founded = servStudent.editStudent(student);
        if (founded == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(founded);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        servStudent.deleteSudent(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<Collection<Student>> studentsByAge(@RequestParam(required = false) int age) {
        if (age < 8) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(servStudent.studentsByAge(age));
    }
    @GetMapping("allStudents")
    public Collection<Student> allStudent() {
        return servStudent.allStudents();
    }

    @GetMapping("studentsBetweenAge")
    public Collection<Student> findByAgeBetween(@RequestParam int min, @RequestParam int max) {
        return servStudent.findByAgeBetween(min, max);
    }
    @GetMapping("/studentFacultyById/{id}")
    public Faculty findFacultyByStudentId(@PathVariable Long id) {
        return servStudent.findFacultyByStudentId(id);
    }

    @GetMapping("/studentFaculty")
    public Faculty findFacultyByStudent(@RequestBody Student student) {
        return servStudent.findFacultyByStudent(student);
    }
}

