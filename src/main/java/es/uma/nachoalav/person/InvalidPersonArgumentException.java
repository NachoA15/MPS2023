package es.uma.nachoalav.person;

public class InvalidPersonArgumentException extends RuntimeException {

    public InvalidPersonArgumentException() {
        super();
    }

    public InvalidPersonArgumentException(String errorMessage) {
        super(errorMessage);
    }
}
