package hogwartsschoolhw33.service;

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

    public Optional<Student> findStudent(Long id) {
        return studentRepository.findById(id);
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
}
