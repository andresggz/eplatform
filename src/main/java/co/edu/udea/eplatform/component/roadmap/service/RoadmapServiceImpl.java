package co.edu.udea.eplatform.component.roadmap.service;

import co.edu.udea.eplatform.component.roadmap.model.Roadmap;
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

    private void activateOrNot(@NotNull Roadmap roadmapToCreate) {
        roadmapToCreate.setActive(true);
    }

}
