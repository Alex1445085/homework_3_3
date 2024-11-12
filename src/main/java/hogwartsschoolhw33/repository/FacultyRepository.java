package hogwartsschoolhw33.repository;

import hogwartsschoolhw33.model.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface FacultyRepository extends JpaRepository<Faculty, Long> {
    Set<Faculty> findByColor(String color);
}
