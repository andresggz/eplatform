package co.edu.udea.eplatform.component.roadmap.service.model;

import co.edu.udea.eplatform.component.roadmap.model.CourseId;
import lombok.*;

@Data
@Generated
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CourseIdSaveCmd {

    private Long id;

    private String name;

    private String description;

    private String level;

    private String iconId;

    private Boolean active;

    public static CourseId toModel(CourseIdSaveCmd courseToRegister){
        return CourseId.builder().id(courseToRegister.getId())
                .name(courseToRegister.getName())
                .description(courseToRegister.getDescription())
                .active(courseToRegister.getActive())
                .iconId(courseToRegister.getIconId())
                .level(courseToRegister.getLevel())
                .build();
    }
}
