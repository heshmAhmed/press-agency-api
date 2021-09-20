package api.press.controller;

import api.press.model.Post;
import api.press.service.SavedPostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RequiredArgsConstructor
@RequestMapping("api/v1/viewers/")
@Controller
public class ViewerController {
    private final SavedPostService savedPostService;

    @PostMapping("saved-posts")
    public ResponseEntity addSavedPost(@RequestBody Map<String, Integer> requestBody){
        savedPostService.savePost(requestBody.get("postId"));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("saved-posts")
    public ResponseEntity<List<Post>> getSavedPosts(){
        return new ResponseEntity<>(savedPostService.getSavedPosts(), HttpStatus.OK);
    }
    @DeleteMapping("saved-posts/{postId}")
    public ResponseEntity<HttpStatus> unSavePost(@PathVariable Integer postId){
        savedPostService.unSavePost(postId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
