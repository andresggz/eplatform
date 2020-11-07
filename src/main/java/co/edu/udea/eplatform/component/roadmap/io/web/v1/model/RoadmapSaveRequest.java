package co.edu.udea.eplatform.component.roadmap.io.web.v1.model;

import co.edu.udea.eplatform.component.roadmap.service.model.RoadmapSaveCmd;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Generated
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoadmapSaveRequest {

    @NotNull
    @NotBlank
    @Size(min = 3, max = 45)
    private String name;

    @NotNull
    @NotBlank
    @Size(min = 3, max = 700)
    private String description;

    @NotNull
    @NotBlank
    @Size(min = 3)
    private String detail;

    public static RoadmapSaveCmd toModel(@NotNull RoadmapSaveRequest roadmapToCreate){
        return RoadmapSaveCmd.builder().name(roadmapToCreate.getName())
                .description(roadmapToCreate.getDescription())
                .detail(roadmapToCreate.getDetail())
                .build();
    }
}
