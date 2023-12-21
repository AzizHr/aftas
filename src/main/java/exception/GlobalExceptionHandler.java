package exception;

import jakarta.validation.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {

        String errorMessage = e.getMessage();

        HttpStatus status = HttpStatus.NOT_FOUND;

        return new ResponseEntity<>(errorMessage, status);
    }


    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> runtimeException(RuntimeException e) {

        String errorMessage = e.getMessage();

        HttpStatus status = HttpStatus.BAD_REQUEST;

        return new ResponseEntity<>(errorMessage, status);
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<String> validationException(ValidationException e) {

        String errorMessage = e.getMessage();

        HttpStatus status = HttpStatus.BAD_REQUEST;

        return new ResponseEntity<>(errorMessage, status);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
}



