package api.press.service;

import api.press.model.Post;
import api.press.model.WebToken;
import api.press.repo.IRepo.IPostRepo;
import api.press.util.TokenUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import java.util.List;


@Slf4j
@Service
@RequiredArgsConstructor
public class PostService {
    private final IPostRepo iPostRepo;
    private final TokenUtil tokenUtil;

    public void createPost(Post post){
        WebToken webToken = tokenUtil.getCurrentWebToken();
        post.setEditorName(webToken.getUsername());
        post.setEditorId(webToken.getId());
        log.info("insert: " + post);
        this.iPostRepo.insert(post).orElseThrow(() -> new RuntimeException("Bad content body!"));
    }

    public List<Post> getWallPosts(){
        return this.iPostRepo.getWallPosts();
    }

    public void deletePost(Integer id) throws RuntimeException {
        int rs = this.iPostRepo.delete(tokenUtil.getCurrentWebToken().getId(), id);
        if(rs == 0)
            throw new RuntimeException("Post not found!");
    }

    public void updatePost(Integer postId, Post post){
        post.setId(postId);
        post.setEditorId(tokenUtil.getCurrentWebToken().getId());
        int rs;
        try {
            rs = this.iPostRepo.update(post);
        }catch (DataIntegrityViolationException | NullPointerException e){
            throw new RuntimeException("Bad body content!");
        }
        if(rs == 0)
            throw new RuntimeException("Post not found!");
    }

    public List<Post> getPosts(){
        return this.iPostRepo.getPosts(tokenUtil.getCurrentWebToken().getId());
    }
}
