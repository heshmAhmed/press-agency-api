package api.press.service;

import api.press.model.Post;
import api.press.repo.IRepo.ISavedPostsRepo;
import api.press.util.TokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import java.util.List;

@RequiredArgsConstructor
@Service
public class SavedPostService {
    private final ISavedPostsRepo savedPostsRepo;
    private final TokenUtil tokenUtil;
    public void savePost(Integer postId) throws RuntimeException {
        try {
            this.savedPostsRepo.save(postId, tokenUtil.getCurrentWebToken().getId());
        }catch (DuplicateKeyException e){
            throw new RuntimeException("Post already saved!");
        }catch (DataIntegrityViolationException e){
            throw new RuntimeException("Bad body content!");
        }
    }

    public List<Post> getSavedPosts() {
        return savedPostsRepo.get(tokenUtil.getCurrentWebToken().getId());
    }

    public void unSavePost(Integer postId) throws RuntimeException {
        int rs = savedPostsRepo.unSavePost(postId, tokenUtil.getCurrentWebToken().getId());
        if(rs == 0)
            throw new RuntimeException("Post already not saved!");
    }
}
