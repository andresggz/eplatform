package co.edu.udea.eplatform.component.career.io.web.v1.model;

import co.edu.udea.eplatform.component.career.service.model.CareerSaveCmd;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Generated
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CareerSaveRequest {

    @NotNull
    @NotBlank
    @Size(min = 3, max = 45)
    private String name;

    @NotNull
    @NotBlank
    @Size(min = 3, max = 700)
    private String description;

    public static CareerSaveCmd toModel(@NotNull CareerSaveRequest roadmapToCreate){
        return CareerSaveCmd.builder().name(roadmapToCreate.getName())
                .description(roadmapToCreate.getDescription())
                .build();
    }
}
