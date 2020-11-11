package co.edu.udea.eplatform.component.career.io.gateway;

import co.edu.udea.eplatform.component.career.io.repository.RoadmapIdRepository;
import co.edu.udea.eplatform.component.career.model.RoadmapId;
import co.edu.udea.eplatform.component.career.service.RoadmapIdGateway;
import co.edu.udea.eplatform.component.shared.web.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;

@Repository
@RequiredArgsConstructor
public class RoadmapIdGatewayImpl implements RoadmapIdGateway {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final static String RESOURCE_NOT_FOUND = "RoadmapId not found.";

    private final RoadmapIdRepository roadmapIdRepository;

    @Override
    public RoadmapId register(@NotNull RoadmapId roadmapIdToRegister) {
        logger.debug("Begin register: roadmapIdToRegister = {}", roadmapIdToRegister);

        RoadmapId roadmapIdRegistered =  roadmapIdRepository.save(roadmapIdToRegister);

        logger.debug("End register: roadmapIdRegistered = {}", roadmapIdRegistered);
        return roadmapIdRegistered;
    }

    @Override
    public RoadmapId findById(@NotNull Long id) {
        logger.debug("Begin findById: id = {}", id);

        RoadmapId roadmapIdFound = roadmapIdRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(RESOURCE_NOT_FOUND));

        logger.debug("End findById: roadmapIdFound = {}",roadmapIdFound);
        return roadmapIdFound;
    }
}
