package co.edu.udea.eplatform.component.roadmap.io.gateway;

import co.edu.udea.eplatform.component.roadmap.io.repository.RoadmapRepository;
import co.edu.udea.eplatform.component.course.model.CourseId;
import co.edu.udea.eplatform.component.roadmap.model.Roadmap;
import co.edu.udea.eplatform.component.roadmap.service.RoadmapGateway;
import co.edu.udea.eplatform.component.roadmap.service.model.RoadmapQuerySearchCmd;
import co.edu.udea.eplatform.component.shared.web.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.Predicate;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.nonNull;

@Repository
@RequiredArgsConstructor
public class RoadmapGatewayImpl implements RoadmapGateway {

    private static final String RESOURCE_NOT_FOUND = "Roadmap not found";

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final RoadmapRepository roadmapRepository;

    @Override
    public Roadmap save(@NotNull Roadmap roadmapToCreate) {
        logger.debug("Begin save: roadmapToCreate = {}", roadmapToCreate);

        final Roadmap roadmapToBeCreated =
                roadmapToCreate.toBuilder().createDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .build();

        final Roadmap roadmapCreated = roadmapRepository.save(roadmapToBeCreated);

        logger.debug("End save: roadmapCreated = {}", roadmapCreated);
        return roadmapCreated;
    }

    @Override
    public Roadmap findById(@NotNull Long id) {
        logger.debug("Begin findById: id = {}", id);

        Roadmap roadmapFound = roadmapRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(RESOURCE_NOT_FOUND));

        logger.debug("End findById: roadmapFound = {}", roadmapFound);
        return roadmapFound;
    }

    @Override
    public Page<Roadmap> findByParameters(@NotNull RoadmapQuerySearchCmd queryCriteria, @NotNull Pageable pageable) {
        logger.debug("Begin findByParameters: queryCriteria = {}. pageable = {}", queryCriteria, pageable);

        Specification<Roadmap> specification = buildCriteria(queryCriteria);

        Page<Roadmap> roadmapsFound = roadmapRepository.findAll(specification, pageable);

        logger.debug("End findByParameters: roadmapsFound = {}", roadmapsFound);
        return roadmapsFound;
    }

    @Override
    public Roadmap addCourse(@NotNull Long roadmapId, @NotNull CourseId courseIdInDataBase) {
        logger.debug("Begin addCourse: roadmapId = {}, courseIdInDataBase = {}", roadmapId, courseIdInDataBase);

        Roadmap roadmapToBeUpdated = findById(roadmapId);

        roadmapToBeUpdated.getCourseIds().add(courseIdInDataBase);

        Roadmap roadmapUpdated = roadmapRepository.save(roadmapToBeUpdated);

        logger.debug("End addCourse: roadmapUpdated = {}", roadmapUpdated);
        return roadmapUpdated;
    }

    private Specification<Roadmap> buildCriteria(RoadmapQuerySearchCmd queryCriteria) {
        logger.debug("Begin buildCriteria: queryCriteria = {}", queryCriteria);

        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if(nonNull(queryCriteria.getName())){
                predicates
                        .add(criteriaBuilder.and(
                                criteriaBuilder.like(
                                        criteriaBuilder.upper(root.get("name")), "%" + queryCriteria.getName().toUpperCase() + "%")));
            }

            if(nonNull(queryCriteria.getActive())){
                predicates
                        .add(criteriaBuilder.and(
                                criteriaBuilder.equal(root.get("active"),  queryCriteria.getActive())));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        };

    }
}
