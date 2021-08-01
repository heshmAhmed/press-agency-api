package api.press.controller;

import api.press.model.Actor;
import api.press.service.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ActorController {

    private final ActorService actorService;

    public ActorController(ActorService actorService) {
        this.actorService = actorService;
    }

    @PostMapping("/api/actor/insert")
    public  ResponseEntity<Actor> insertActor(@RequestBody Actor actor){
        try {
            return new ResponseEntity<>(actorService.insertActor(actor), HttpStatus.ACCEPTED);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PostMapping("/api/actor/update")
    public ResponseEntity<Actor> updateActor(@RequestBody Actor actor){
        try {
            return new ResponseEntity<>(actorService.updateActor(actor), HttpStatus.ACCEPTED);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
