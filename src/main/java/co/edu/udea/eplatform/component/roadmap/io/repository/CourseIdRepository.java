package co.edu.udea.eplatform.component.roadmap.io.repository;

import co.edu.udea.eplatform.component.course.model.CourseId;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseIdRepository extends PagingAndSortingRepository<CourseId, Long> {
}
