package co.edu.udea.eplatform.component.career.service.model;

import co.edu.udea.eplatform.component.career.model.RoadmapId;
import lombok.*;

import javax.validation.constraints.NotNull;

@Data
@Generated
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoadmapIdSaveCmd {

    @NotNull
    private Long id;

    @NotNull
    private String name;

    private String description;

    public static RoadmapId toModel(RoadmapIdSaveCmd roadmapIdToRegister){
        return RoadmapId.builder().id(roadmapIdToRegister.getId())
                .name(roadmapIdToRegister.getName()).description(roadmapIdToRegister.getDescription())
                .build();
    }
}
