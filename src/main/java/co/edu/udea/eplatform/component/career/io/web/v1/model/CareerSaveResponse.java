package co.edu.udea.eplatform.component.career.io.web.v1.model;

import co.edu.udea.eplatform.component.career.model.Career;
import co.edu.udea.eplatform.component.career.model.RoadmapIdCareer;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDateTime;

@JsonInclude(Include.NON_NULL)
@Data
@Generated
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CareerSaveResponse extends RepresentationModel<CareerSaveResponse> {

    private Long id;

    private String name;

    private String description;

    private String iconId;

    private Boolean active;

    private Integer totalRoadmaps;

    private LocalDateTime createDate;

    private LocalDateTime updateDate;

    public static CareerSaveResponse fromModel(Career career){
        return CareerSaveResponse.builder().id(career.getId())
                .name(career.getName()).description(career.getDescription())
                .totalRoadmaps(career.getRoadmapIds().size())
                .createDate(career.getCreateDate()).updateDate(career.getUpdateDate())
                .build();
    }
}
