package api.press.Exception;

import lombok.Getter;

@Getter
public class ActorException extends RuntimeException{
    public ActorException(String message) {
        super(message);
    }
}
