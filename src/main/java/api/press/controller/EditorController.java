package api.press.controller;

import api.press.model.Post;
import api.press.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
@RequestMapping("api/v1/editors")
public class EditorController {
    private final PostService postService;

    @PostMapping("/posts")
    public ResponseEntity<HttpStatus> createPost(@RequestBody Post post){
        this.postService.createPost(post);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
