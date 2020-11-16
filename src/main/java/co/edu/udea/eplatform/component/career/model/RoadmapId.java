package co.edu.udea.eplatform.component.career.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Data
@Generated
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class RoadmapId {

    @Id
    private Long id;

    @NotNull
    private String name;

    private String description;

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
