package api.press.service;

import api.press.model.Actor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;

public interface IActorService extends UserDetailsService {
    Actor insert(Actor actor);
    Actor update(Actor actor);
    UserDetails loadUserByEmail(String email) throws UserPrincipalNotFoundException;
    List<Actor> getAllActors();
}
