package api.press.service;

import api.press.Exception.PostException;
import api.press.model.Post;
import api.press.repo.IRepo.IPostRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.Date;

@Slf4j
@Service
@RequiredArgsConstructor
public class PostService {
    private final IPostRepo iPostRepo;

    public void createPost(Post post){
        post.setNo_views(0);
        post.setNo_dislikes(0);
        post.setNo_likes(0);
        post.setCreate_date(new Date(System.currentTimeMillis()));
        log.info("insert: " + post);
        this.iPostRepo.insert(post).orElseThrow(() -> new PostException("Bad Content Body"));
    }
}
