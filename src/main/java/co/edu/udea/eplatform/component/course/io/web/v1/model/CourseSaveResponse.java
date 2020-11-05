package co.edu.udea.eplatform.component.course.io.web.v1.model;

import co.edu.udea.eplatform.component.course.model.Course;
import co.edu.udea.eplatform.component.course.model.Level;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.*;

import java.time.LocalDateTime;

@JsonInclude(Include.NON_NULL)
@Data
@Generated
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseSaveResponse {

    private Long id;

    private String name;

    private String description;

    private Level level;

    private String iconId;

    private Boolean active;

    private LocalDateTime releaseDate;

    private LocalDateTime createDate;

    private LocalDateTime updateDate;

    public static CourseSaveResponse fromModel(Course course){
        return CourseSaveResponse.builder().id(course.getId())
                .name(course.getName()).description(course.getDescription())
                .level(course.getLevel()).iconId(course.getImageId())
                .active(course.getActive()).releaseDate(course.getReleaseDate())
                .createDate(course.getCreateDate()).updateDate(course.getUpdateDate())
                .build();
    }
}
