package api.press.controller;

import api.press.model.Actor;
import api.press.service.ActorServiceImp;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
public class ActorController {

    private final ActorServiceImp actorService;

    public ActorController(ActorServiceImp actorService) {
        this.actorService = actorService;
    }

    @PostMapping("/api/actor/insert")
    public  ResponseEntity<Actor> insertActor(@RequestBody Person person){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("api/actor/insert").toUriString());
        try {
            return ResponseEntity.created(uri).body(actorService.insert(person));
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/api/actor/update")
    public ResponseEntity<Actor> updateActor(@RequestBody Actor actor){
        try {
            return new ResponseEntity<>(actorService.update(actor), HttpStatus.ACCEPTED);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}

@AllArgsConstructor
class Person extends Actor{

}
