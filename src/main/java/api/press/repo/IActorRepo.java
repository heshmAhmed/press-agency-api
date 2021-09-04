package api.press.repo;

import api.press.model.Actor;

import java.util.List;
import java.util.Optional;

public interface IActorRepo {
    Optional<Actor> findByEmail(String email);
    Optional<Actor> findByUsername(String username);
    Optional<Actor> findByID(int id);
    Optional<Actor> insert(Actor actor);
    Optional<Actor> update(Actor actor);
    List<Actor> getAllActors();
}
