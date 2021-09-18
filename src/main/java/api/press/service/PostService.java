package api.press.service;

import api.press.Exception.PostException;
import api.press.model.Post;
import api.press.model.WebToken;
import api.press.repo.IRepo.IPostRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;


@Slf4j
@Service
@RequiredArgsConstructor
public class PostService {
    private final IPostRepo iPostRepo;

    public void createPost(Post post){
        WebToken webToken = (WebToken) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        post.setEditorName(webToken.getUsername());
        post.setEditorId(webToken.getId());
        log.info("insert: " + post);
        this.iPostRepo.insert(post).orElseThrow(() -> new PostException("Bad Content Body"));
    }

    public List<Post> getWallPosts(){
        return this.iPostRepo.getWallPosts();
    }
}
