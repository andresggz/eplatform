package co.edu.udea.eplatform.component.roadmap.service;

import co.edu.udea.eplatform.component.roadmap.model.Roadmap;
import co.edu.udea.eplatform.component.roadmap.service.model.CourseAddCmd;
import co.edu.udea.eplatform.component.roadmap.service.model.RoadmapQuerySearchCmd;
import co.edu.udea.eplatform.component.roadmap.service.model.RoadmapSaveCmd;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.validation.constraints.NotNull;

public interface RoadmapService {

    Roadmap create(@NotNull RoadmapSaveCmd roadmapToCreateCmd);

    Roadmap findById(@NotNull Long id);

    Page<Roadmap> findByParameters(@NotNull RoadmapQuerySearchCmd queryCriteria, @NotNull Pageable pageable);

    Roadmap addCourse(@NotNull Long roadmapId, @NotNull CourseAddCmd courseToAddCmd);
}
