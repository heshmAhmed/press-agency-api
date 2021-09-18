package api.press.service;

import api.press.Exception.PostException;
import api.press.model.Post;
import api.press.repo.IRepo.IPostRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;


@Slf4j
@Service
@RequiredArgsConstructor
public class PostService {
    private final IPostRepo iPostRepo;

    public void createPost(Post post){
        log.info("insert: " + post);
        this.iPostRepo.insert(post).orElseThrow(() -> new PostException("Bad Content Body"));
    }

    public List<Post> getWallPosts(){
        return this.iPostRepo.getWallPosts();
    }
}
