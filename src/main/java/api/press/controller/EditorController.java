package api.press.controller;

import api.press.model.Post;
import api.press.model.WebToken;
import api.press.service.PostService;
import api.press.util.TokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Controller
@RequestMapping("api/v1/editors")
public class EditorController {
    private final PostService postService;
    private final TokenUtil tokenUtil;

    @PostMapping("/posts")
    public ResponseEntity<HttpStatus> createPost(@RequestBody Post post){
        this.postService.createPost(post);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/posts/{id}")
    public ResponseEntity<HttpStatus> deletePost(@PathVariable Integer id){
        this.postService.deletePost(tokenUtil.getCurrentWebToken().getId(), id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
