package co.edu.udea.eplatform.component.career.service;

import co.edu.udea.eplatform.component.career.model.RoadmapId;
import co.edu.udea.eplatform.component.career.service.model.RoadmapIdSaveCmd;

import javax.validation.constraints.NotNull;

public interface RoadmapIdService {

    RoadmapId registerRoadmapId(@NotNull RoadmapIdSaveCmd roadmapIdToRegisterCmd);

    RoadmapId findById(@NotNull Long id);
}
