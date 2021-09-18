package api.press.controller;

import api.press.service.SavedPostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
