package co.edu.udea.eplatform.component.course.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Data
@Generated
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class CourseId {

    @Id
    private Long id;

    @NotNull
    private String name;

    private String description;

    private String level;

    private String iconId;

    private Boolean active;

    private LocalDateTime createDate;

    private LocalDateTime updateDate;

    @PrePersist
    public void prePersist(){
        createDate = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate(){
        updateDate = LocalDateTime.now();
    }
}
