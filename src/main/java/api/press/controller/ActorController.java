package api.press.controller;

import api.press.model.Actor;
import api.press.model.Post;
import api.press.model.PostType;
import api.press.repo.ActorRepo;
import api.press.repo.PostRepo;
import api.press.service.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.time.LocalDate;

@RestController
public class ActorController {

    @Autowired
    private ActorService actorService;


    @PostMapping("/api/actor/update")
    public ResponseEntity updateActor(@RequestBody Actor actor){
        try {
            return new ResponseEntity(actorService.updateActor(actor), HttpStatus.ACCEPTED);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity("Wrong credentials",HttpStatus.BAD_REQUEST);
        }
    }
}
