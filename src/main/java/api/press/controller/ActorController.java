package api.press.controller;

import api.press.model.Actor;
import api.press.model.Answer;
import api.press.model.Post;
import api.press.service.AnswerService;
import api.press.service.IActorService;
import api.press.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;
import java.util.List;

@Slf4j
@RequestMapping("api/v1")
@RestController
@RequiredArgsConstructor
public class ActorController {
    private final IActorService actorService;
    private final PostService postService;

    @PostMapping("/actors")
    public ResponseEntity<Actor> register(@RequestBody Actor person) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("api/actor/insert").toUriString());
        return ResponseEntity.created(uri).body(actorService.createActor(person));
    }

    @PutMapping("/actors")
    public ResponseEntity<HttpStatus> update(@RequestBody Actor actor){
        actorService.updateActor(actor);
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @GetMapping("/actors")
    public ResponseEntity<List<Actor>> GetUsers(){
        return new ResponseEntity<>(actorService.getAllActors(),HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/actors/{actorId}")
    public ResponseEntity<HttpStatus> DeleteActor(@PathVariable Integer actorId){
       actorService.deleteActor(actorId);
       return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/wall")
    public ResponseEntity<List<Post>> Wall(){
        return new ResponseEntity<>(postService.getWallPosts(), HttpStatus.OK);
    }


}
