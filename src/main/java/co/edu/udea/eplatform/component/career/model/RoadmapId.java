package co.edu.udea.eplatform.component.career.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@Generated
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class RoadmapId {

    @Id
    private Long id;
}
