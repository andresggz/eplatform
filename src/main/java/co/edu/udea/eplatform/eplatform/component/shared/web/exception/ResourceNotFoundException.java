package co.edu.udea.eplatform.eplatform.component.shared.web.exception;

public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(){}

    public ResourceNotFoundException(String message){
        super(message);
    }
}
