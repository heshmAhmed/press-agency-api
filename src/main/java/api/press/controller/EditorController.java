package api.press.controller;

import api.press.model.Answer;
import api.press.model.Post;
import api.press.service.AnswerService;
import api.press.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("api/v1/editors")
public class EditorController {
    private final PostService postService;
    private final AnswerService answerService;


    @PostMapping("/posts")
    public ResponseEntity<HttpStatus> createPost(@RequestBody Post post){
        this.postService.createPost(post);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/posts/{id}")
    public ResponseEntity<HttpStatus> deletePost(@PathVariable Integer id){
        this.postService.deletePost(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/posts/{id}")
    public ResponseEntity<HttpStatus> updatePost(@PathVariable Integer id, @RequestBody Post post){
        postService.updatePost(id, post);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/posts")
    public  ResponseEntity<List<Post>> getHistory(){
        return new ResponseEntity<>(postService.getPosts(), HttpStatus.OK);
    }

    @PostMapping("/posts/questions/{questionId}/answers")
    public ResponseEntity<Answer> addAnswer(@PathVariable Integer questionId,
                                            @RequestBody Answer answer){
        return new ResponseEntity<>(answerService.addAnswer(questionId, answer), HttpStatus.CREATED);
    }

    @PutMapping("/posts/questions/answers")
    public ResponseEntity<HttpStatus> updateAnswer(@RequestBody Answer answer){
        answerService.updateAnswer(answer);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
