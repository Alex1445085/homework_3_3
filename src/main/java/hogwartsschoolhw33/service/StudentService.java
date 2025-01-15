package hogwartsschoolhw33.service;

import hogwartsschoolhw33.Exception.StudentNotFoundException;
import hogwartsschoolhw33.model.Faculty;
import hogwartsschoolhw33.model.Student;
import hogwartsschoolhw33.repository.StudentRepository;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student findStudent(Long id) {
        return studentRepository.findById(id).
                orElseThrow(() -> new StudentNotFoundException("Not found"));
    }

    public Student addStudent(Student temp) {
        return studentRepository.save(temp);
    }

    public Student editStudent(Student temp) {
        return studentRepository.save(temp);
    }

    public void deleteSudent(Long id) {
        studentRepository.deleteById(id);
    }
    
    public Collection<Student> studentsByAge(int age) {
        Set<Student> result = studentRepository.findByAge(age);
        return Collections.unmodifiableCollection(result);
    }

    public Collection<Student> allStudents() {
        return studentRepository.findAll();
    }

    public Collection<Student> findByAgeBetween(int min, int max) {
        return studentRepository.findByAgeBetween(min, max);
    }

    public Faculty findFacultyByStudentId(long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException("Not found"))
                .getFaculty();
    }

    public Faculty findFacultyByStudent(Student student) {
        Faculty temp = new Faculty();
        temp = studentRepository.findById(student.getId())
                .orElseThrow(() -> new StudentNotFoundException("Not found"))
                .getFaculty();
        return temp;
    }

    public double avgAgeOfStudents() {
        return studentRepository.avgAgeOfStudents();
    }

    public int totalAmountOfStudent() {
        return studentRepository.totalAmountOfStudents();
    }

    public Collection<Student> getLastFive(int lim) {
//        int lim = 5;
//        int offSet = studentRepository.totalAmountOfStudents() - lim;
        return studentRepository.getLastFive(lim);
    }
}