package hogwartsschoolhw33.repository;

import hogwartsschoolhw33.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.Set;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Set<Student> findByAge(int age);

    Set<Student> findByAgeBetween(int min, int max);

    Collection<Student> findAllByFaculty_id(Long id);

    @Query(value = "SELECT AVG(age) FROM student", nativeQuery = true)
    double avgAgeOfStudents();

    @Query(value = "SELECT COUNT(*) FROM student", nativeQuery = true)
    int totalAmountOfStudents();

    @Query(value = "SELECT * FROM student ORDER BY id DESC LIMIT :lim", nativeQuery = true)
    Collection<Student> getLastFive(@Param("lim") int lim);
}
