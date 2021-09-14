package api.press.controller;

import api.press.model.Actor;
import api.press.service.ActorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;
import java.util.List;

@Slf4j
@RequestMapping("api/v1/actors")
@RestController
@RequiredArgsConstructor
public class ActorController {
    private final ActorService actorService;

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody Actor person) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("api/actor/insert").toUriString());
        return ResponseEntity.created(uri).body(actorService.insert(person));
    }

    @PutMapping
    public ResponseEntity update(@RequestBody Actor actor){
        actorService.update(actor);
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @GetMapping
    public ResponseEntity<List<Actor>> GetUsers(){
        return new ResponseEntity<>(actorService.getAllActors(),HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{actorId}")
    public ResponseEntity DeleteActor(@PathVariable Integer actorId){
       actorService.delete(actorId);
       return new ResponseEntity<>(HttpStatus.OK);
    }
}
