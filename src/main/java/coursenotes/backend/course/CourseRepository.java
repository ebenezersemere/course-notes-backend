package coursenotes.backend.course;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CourseRepository extends JpaRepository<Course, UUID> {
//    @Query("SELECT c FROM Course c INNER JOIN c.users u WHERE u.id = :userId")
//    List<Course> findCoursesByUserId(@Param("userId") UUID userId);
}