package hogwartsschoolhw33.service;

import hogwartsschoolhw33.Exception.StudentNotFoundException;
import hogwartsschoolhw33.model.Faculty;
import hogwartsschoolhw33.model.Student;
import hogwartsschoolhw33.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    private final Logger logger = LoggerFactory.getLogger(StudentService.class);

    public Student findStudent(Long id) {
        logger.warn("invoke method for find student with id:{}", id);
        return studentRepository.findById(id).
                orElseThrow(() -> new StudentNotFoundException("Not found"));
    }

    public Student addStudent(Student temp) {
        logger.warn("invoke method for create {}", temp);
        return studentRepository.save(temp);
    }

    public Student editStudent(Student temp) {
        logger.warn("invoke method for edit {}", temp);
        return studentRepository.save(temp);
    }

    public void deleteSudent(Long id) {
        logger.warn("invoke method for delete student with id:{}", id);
        studentRepository.deleteById(id);
    }

    public Collection<Student> studentsByAge(int age) {
        logger.warn("invoke method for find student by age:{}", age);
        Set<Student> result = studentRepository.findByAge(age);
        return Collections.unmodifiableCollection(result);
    }

    public Collection<Student> allStudents() {
        logger.warn("invoke method find all students");
        Collection<Student> temp = studentRepository.findAll();
        return temp;
    }

    public Collection<Student> findByAgeBetween(int min, int max) {
        Collection<Student> temp = studentRepository.findByAgeBetween(min, max);
        logger.warn("invoke method find students with age between");
        return temp;
    }

    public Faculty findFacultyByStudentId(long id) {
        logger.warn("invoke method find faculty by student id");
        return studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException("Not found"))
                .getFaculty();
    }

    public Faculty findFacultyByStudent(Student student) {
        logger.warn("invoke method find student's faculty");
        Faculty temp = new Faculty();
        temp = studentRepository.findById(student.getId())
                .orElseThrow(() -> new StudentNotFoundException("Not found"))
                .getFaculty();
        return temp;
    }

    public double avgAgeOfStudents() {
        logger.warn("invoke method avgAgeOfStudents");
        return studentRepository.avgAgeOfStudents();
    }

    public int totalAmountOfStudent() {
        logger.warn("invoke method total amount of students");
        return studentRepository.totalAmountOfStudents();
    }

    public Collection<Student> getLast(int lim) {
        logger.warn("invoke method get last {} students from list", lim);
        return studentRepository.getLast(lim);
    }
}