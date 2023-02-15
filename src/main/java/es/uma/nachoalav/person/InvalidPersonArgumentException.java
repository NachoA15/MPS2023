package es.uma.nachoalav.person;

public class InvalidPersonArgumentException extends RuntimeException {
    public InvalidPersonArgumentException(String errorMessage) {
        super(errorMessage);
    }
}
