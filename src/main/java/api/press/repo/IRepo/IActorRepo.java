package api.press.repo.IRepo;

import api.press.Exception.ActorException;
import api.press.model.Actor;

import java.util.List;
import java.util.Optional;

public interface IActorRepo {
    Optional<Actor> findByEmail(String email);
    Optional<Actor> findByUsername(String username);
    Optional<Actor> findByID(int id);
    Optional<Actor> insert(Actor actor);
    int update(Actor actor) throws ActorException;
    List<Actor> getAllActors();
    void delete(Integer actorId) throws ActorException;
}
