package co.edu.udea.eplatform.component.career.service;

import co.edu.udea.eplatform.component.career.model.RoadmapId;

import javax.validation.constraints.NotNull;

public interface RoadmapIdGateway {

    RoadmapId register(@NotNull RoadmapId roadmapIdToRegister);

    RoadmapId findById(@NotNull Long id);
}
