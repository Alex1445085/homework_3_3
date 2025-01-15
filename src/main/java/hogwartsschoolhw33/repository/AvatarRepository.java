package hogwartsschoolhw33.repository;

import hogwartsschoolhw33.model.Avatar;
import hogwartsschoolhw33.service.AvatarService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AvatarRepository extends JpaRepository<Avatar, Long> {
    Optional<Avatar> findByStudentId(Long studentId);
}