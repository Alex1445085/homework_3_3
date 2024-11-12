package hogwartsschoolhw33.service;

import hogwartsschoolhw33.model.Student;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StudentService {
    Map<Long, Student> studentMap = new HashMap<>();
    Long id = 0L;

    public Student findStudent(Long studentId) {
        return studentMap.get(studentId);
    }

    public Student addStudent(Student temp) {
        temp.setId(++id);
        studentMap.put(temp.getId(), temp);
        return temp;
    }

    public Student editStudent(Long studentId, Student temp) {
        temp.setId(studentId);
        if (!studentMap.containsKey(studentId)) {
            return null;
        }
        studentMap.put(studentId, temp);
        return temp;
    }

    public void deleteSudent(Long studentId) {
        studentMap.remove(studentId);
    }

    public Collection<Student> allStudents() {
        return Collections.unmodifiableCollection(studentMap.values());
    }

    public Collection<Student> studentsByAge(int age) {
        Collection<Student> result = new HashSet<>();
        for (Student actual : studentMap.values()) {
            if (age == actual.getAge()) {
                result.add(actual);
            }
        }
        return Collections.unmodifiableCollection(result);
    }
}
