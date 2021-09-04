package api.press.controller;

import api.press.model.Actor;
import api.press.service.ActorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;
import java.util.List;

@RequestMapping("api/v1/actors")
@RestController
@RequiredArgsConstructor
public class ActorController {
    private final ActorService actorService;

    @PostMapping("/register")
    public ResponseEntity createActor(@RequestBody Actor person){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("api/actor/insert").toUriString());
        try {
            return ResponseEntity.created(uri).body(actorService.insert(person));
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    public ResponseEntity<Actor> updateActor(@RequestBody Actor actor){
        try {
            return new ResponseEntity<>(actorService.update(actor), HttpStatus.ACCEPTED);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<List<Actor>> GetUsers(){
        return new ResponseEntity<>(actorService.getAllActors(),HttpStatus.ACCEPTED);
    }

}
