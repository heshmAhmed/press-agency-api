package api.press.repo.IRepo;

import api.press.model.Actor;
import java.util.List;
import java.util.Optional;

public interface IActorRepo {
    Optional<Actor> findByEmail(String email);
    Optional<Actor> findByUsername(String username);
    Optional<Actor> findByID(int id);
    Optional<Actor> insert(Actor actor);
    int update(Actor actor);
    List<Actor> getAllActors();
    int delete(Integer actorId);
}
