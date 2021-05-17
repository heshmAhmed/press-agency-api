package api.press.controller;

import api.press.model.Actor;
import api.press.service.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ActorController {

    @Autowired
    private ActorService actorService;
    @PostMapping("/api/actor/update")
    public ResponseEntity<Actor> updateActor(@RequestBody Actor actor){
        try {
            return new ResponseEntity<>(actorService.updateActor(actor), HttpStatus.ACCEPTED);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
