package api.press.controller;

import api.press.Exception.ActorException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class ActorControllerAdvice {
    @ExceptionHandler(ActorException.class)
    public ResponseEntity<String> handleActorException(ActorException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST );
    }
}
