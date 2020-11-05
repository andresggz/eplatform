package co.edu.udea.eplatform.component.course.io.web.v1.model;

import co.edu.udea.eplatform.component.course.model.CourseLevel;
import co.edu.udea.eplatform.component.course.service.model.CourseSaveCmd;
import lombok.*;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
@Generated
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CourseSaveRequest {

    @NotNull
    @NotBlank
    @Size(min = 3, max = 45)
    private String name;

    @NotNull
    @NotBlank
    @Size(min = 3, max = 700)
    private String description;

    @NotNull
    private CourseLevel level;

    @Future
    private LocalDateTime releaseDate;

    public static CourseSaveCmd toModel(CourseSaveRequest courseToCreate) {
        return CourseSaveCmd.builder().name(courseToCreate.getName())
                .description(courseToCreate.getDescription())
                .level(courseToCreate.getLevel())
                .releaseDate(courseToCreate.getReleaseDate())
                .build();
    }
}
