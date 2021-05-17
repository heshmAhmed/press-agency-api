package api.press.service;

import api.press.model.Actor;
import api.press.repo.ActorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ActorService {
    @Autowired
    private ActorRepo actorRepo;

    public Actor InsertActor(Actor actor){
        actorRepo.insert(actor).orElseThrow(() -> new RuntimeException("user already exists"));
        return actor;
    }

    public Actor updateActor(Actor actor){
        actorRepo.update(actor).orElseThrow(()->new RuntimeException("actor with id " + actor.getId() + " is not found!"));
        return actor;
    }
}
