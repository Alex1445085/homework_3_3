package hogwartsschoolhw33.repository;

import hogwartsschoolhw33.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.Set;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Set<Student> findByAge(int age);
    Set<Student> findByAgeBetween(int min, int max);
    Collection<Student> findAllByFaculty_id(Long id);
}
