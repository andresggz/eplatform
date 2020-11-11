package co.edu.udea.eplatform.component.career.io.repository;

import co.edu.udea.eplatform.component.career.model.RoadmapId;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoadmapIdRepository extends PagingAndSortingRepository<RoadmapId, Long> {
}
