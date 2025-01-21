package hogwartsschoolhw33.repository;

import hogwartsschoolhw33.model.Avatar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.Collection;
import java.util.Optional;

public interface AvatarRepository extends JpaRepository<Avatar, Long> {
    Optional<Avatar> findByStudentId(Long studentId);

    @Query(value = "SELECT * FROM avatar ORDER BY id ASC OFFSET :offSet LIMIT :size", nativeQuery = true)
    Collection<Avatar> avatarsByPage(@Param("offSet") int offSet, @Param("size") int size);
}