package api.press.service;

import api.press.Exception.PostException;
import api.press.model.Post;
import api.press.model.WebToken;
import api.press.repo.IRepo.IPostRepo;
import api.press.util.TokenUtil;
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
    private final TokenUtil tokenUtil;

    public void createPost(Post post){
        WebToken webToken = tokenUtil.getCurrentWebToken();
        post.setEditorName(webToken.getUsername());
        post.setEditorId(webToken.getId());
        log.info("insert: " + post);
        this.iPostRepo.insert(post).orElseThrow(() -> new PostException("Bad Content Body!"));
    }

    public List<Post> getWallPosts(){
        return this.iPostRepo.getWallPosts();
    }

    public void deletePost(Integer editorId, Integer id) throws PostException {
        int rs = this.iPostRepo.delete(editorId, id);
        if(rs == 0)
            throw new PostException("Post Not Found!");

    }
}
