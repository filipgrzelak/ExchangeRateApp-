package pl.kurs.exceptions;

public class InvalidDataInputException extends RuntimeException{
    public InvalidDataInputException() {
    }

    public InvalidDataInputException(String message) {
        super(message);
    }
}
