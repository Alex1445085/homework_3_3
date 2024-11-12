package hogwartsschoolhw33.repository;

import hogwartsschoolhw33.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
