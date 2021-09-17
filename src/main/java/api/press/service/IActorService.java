package api.press.service;

import api.press.Exception.ActorException;
import api.press.model.Actor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;

public interface IActorService extends UserDetailsService {
    Actor createActor(Actor actor) throws ActorException;
    void updateActor(Actor actor) throws ActorException;
    UserDetails loadUserByEmail(String email) throws UserPrincipalNotFoundException;
    List<Actor> getAllActors();
    void deleteActor(Integer actorId) throws ActorException;
}
