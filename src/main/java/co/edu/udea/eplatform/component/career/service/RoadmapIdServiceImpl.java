package co.edu.udea.eplatform.component.career.service;

import co.edu.udea.eplatform.component.career.model.RoadmapId;
import co.edu.udea.eplatform.component.career.service.model.RoadmapIdSaveCmd;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;

@Service
@Transactional
@RequiredArgsConstructor
public class RoadmapIdServiceImpl implements RoadmapIdService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final RoadmapIdGateway roadmapIdGateway;

    @Override
    public RoadmapId registerRoadmapId(@NotNull RoadmapIdSaveCmd roadmapIdToRegisterCmd) {
        logger.debug("Begin registerRoadmapId: roadmapIdToRegisterCmd = {}", roadmapIdToRegisterCmd);

        RoadmapId roadmapIdToRegister = RoadmapIdSaveCmd.toModel(roadmapIdToRegisterCmd);

        RoadmapId roadmapIdRegistered = roadmapIdGateway.register(roadmapIdToRegister);

        logger.debug("End registerRoadmapId: roadmapIdRegistered = {}", roadmapIdRegistered);
        return roadmapIdRegistered;
    }

    @Override
    @Transactional(readOnly = true)
    public RoadmapId findById(@NotNull Long id) {
        logger.debug("Begin findById: id = {}", id);

        RoadmapId roadmapIdFound = roadmapIdGateway.findById(id);

        logger.debug("End findById: roadmapIdFound = {}", roadmapIdFound);
        return roadmapIdFound;
    }
}
