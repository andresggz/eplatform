package co.edu.udea.eplatform.component.career.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

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
}
