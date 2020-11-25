package co.edu.udea.eplatform.component.roadmap.service;

import co.edu.udea.eplatform.component.roadmap.model.CourseIdRoadmap;
import co.edu.udea.eplatform.component.roadmap.model.Roadmap;
import co.edu.udea.eplatform.component.roadmap.service.model.CourseAddCmd;
import co.edu.udea.eplatform.component.roadmap.service.model.RoadmapAddedToCareerCmd;
import co.edu.udea.eplatform.component.roadmap.service.model.RoadmapQuerySearchCmd;
import co.edu.udea.eplatform.component.roadmap.service.model.RoadmapSaveCmd;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;


@Service
@Transactional
@RequiredArgsConstructor
public class RoadmapServiceImpl implements RoadmapService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final RoadmapEventPublisher roadmapEventPublisher;

    private final RoadmapGateway roadmapGateway;

    private final CourseIdRoadmapService courseIdService;

    @Override
    public Roadmap create(@NotNull RoadmapSaveCmd roadmapToCreateCmd) {
        logger.debug("Begin create: roadmapToCreateCmd");

        Roadmap roadmapToCreate = RoadmapSaveCmd.toModel(roadmapToCreateCmd);

        activateOrNot(roadmapToCreate);

        Roadmap roadmapCreated = roadmapGateway.save(roadmapToCreate);

        roadmapEventPublisher.publishRoadmapCreated(roadmapCreated);

        logger.debug("End create: roadmapCreated = {}", roadmapCreated);
        return roadmapCreated;
    }

    @Override
    @Transactional(readOnly = true)
    public Roadmap findById(@NotNull Long id) {
        logger.debug("Begin findById = {}", id);

        Roadmap roadmapFound = roadmapGateway.findById(id);

        logger.debug("End findById: roadmapFound = {}", roadmapFound);
        return roadmapFound;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Roadmap> findByParameters(@NotNull RoadmapQuerySearchCmd queryCriteria, @NotNull Pageable pageable) {
        logger.debug("Begin findByParameters: queryCriteria = {}, pageable = {}", queryCriteria, pageable);

        Page<Roadmap> roadmapsFound = roadmapGateway.findByParameters(queryCriteria, pageable);

        logger.debug("End findByParameters: roadmapsFound = {}", roadmapsFound);
        return roadmapsFound;
    }

    @Override
    public Roadmap addCourse(@NotNull Long roadmapId, @NotNull CourseAddCmd courseToAddCmd) {
        logger.debug("Begin addCourse: roadmapId = {}, courseToAddCmd = {}", roadmapId, courseToAddCmd);

        final Long courseIdToAdd = courseToAddCmd.getCourseId();

        CourseIdRoadmap courseIdRoadmapInDataBase = courseIdService.findById(courseIdToAdd);

        Roadmap roadmapUpdated = roadmapGateway.addCourse(roadmapId, courseIdRoadmapInDataBase);

        logger.debug("End addCourse: roadmapUpdated = {}", roadmapUpdated);
        return roadmapUpdated;
    }

    @Override
    public Roadmap update(@NotNull RoadmapAddedToCareerCmd roadmapAddedToCareerCmd) {
        logger.debug("Begin update: roadmapAddedToCareerCmd = {}", roadmapAddedToCareerCmd);

        Long roadmapIdAddedToCareer = roadmapAddedToCareerCmd.getId();

        Roadmap roadmapInDataBase = findById(roadmapIdAddedToCareer);

        Roadmap roadmapToUpdate = roadmapInDataBase.toBuilder()
                .isLinkedToRoute(true)
                .build();

        Roadmap roadmapUpdated = roadmapGateway.update(roadmapToUpdate);

        logger.debug("End update: roadmapUpdated = {}", roadmapUpdated);

        return roadmapUpdated;
    }

    private void activateOrNot(@NotNull Roadmap roadmapToCreate) {
        roadmapToCreate.setActive(true);
    }

}
