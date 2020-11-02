package co.edu.udea.eplatform.component.shared.model;

import lombok.Generated;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Generated
public class ErrorDetails {

    private LocalDate timestamp;
    private String message;
    private String details;
    private String type;

    public ErrorDetails(LocalDate timestamp, String message, String details, String type) {
        super();
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
        this.type = type;
    }
}
