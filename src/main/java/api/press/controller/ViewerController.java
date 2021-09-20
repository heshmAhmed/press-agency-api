package api.press.controller;

import api.press.model.Post;
import api.press.model.Question;
import api.press.service.QuestionService;
import api.press.service.SavedPostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;


@RequiredArgsConstructor
@RequestMapping("api/v1")
@Controller
public class ViewerController {
    private final SavedPostService savedPostService;
    private final QuestionService questionService;

    @PostMapping("/viewers/saved-posts")
    public ResponseEntity addSavedPost(@RequestBody Map<String, Integer> requestBody){
        savedPostService.savePost(requestBody.get("postId"));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/viewers/saved-posts")
    public ResponseEntity<List<Post>> getSavedPosts(){
        return new ResponseEntity<>(savedPostService.getSavedPosts(), HttpStatus.OK);
    }

    @DeleteMapping("/viewers/saved-posts/{postId}")
    public ResponseEntity<HttpStatus> unSavePost(@PathVariable Integer postId){
        savedPostService.unSavePost(postId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/posts/{postId}/questions")
    public ResponseEntity<Question> addQuestion(@PathVariable Integer postId,
                                                @RequestBody Question question){
        return new ResponseEntity<>(questionService.addQuestion(question, postId), HttpStatus.CREATED);
    }
}
