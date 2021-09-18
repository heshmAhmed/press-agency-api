package api.press.controller;

import api.press.Exception.ActorException;
import api.press.Exception.PostException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class GlobalControllerAdvice {
    @ExceptionHandler(ActorException.class)
    public ResponseEntity<String> handleActorException(ActorException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST );
    }

    @ExceptionHandler(PostException.class)
    public ResponseEntity<String> handlePostException(PostException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleSavedPostException(RuntimeException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
