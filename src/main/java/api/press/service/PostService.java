package api.press.service;

import api.press.model.Post;
import api.press.repo.PostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostService {
    @Autowired
    private PostRepo postRepo;

    public Post insertPost(Post post){
        postRepo.insert(post).orElseThrow(()-> new RuntimeException("editor with id " + post.getEditorID() + " is not found!"));
        return post;
    }
}
