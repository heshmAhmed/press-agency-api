package api.press.service;

import api.press.Exception.ActorException;
import api.press.model.Actor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;

public interface IActorService extends UserDetailsService {
    Actor insert(Actor actor) throws ActorException;
    void update(Actor actor) throws ActorException;
    UserDetails loadUserByEmail(String email) throws UserPrincipalNotFoundException;
    List<Actor> getAllActors();
    void delete(Integer actorId) throws ActorException;
}
