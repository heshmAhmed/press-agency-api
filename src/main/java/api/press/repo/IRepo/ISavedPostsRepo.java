package api.press.repo.IRepo;

import api.press.model.Post;

import java.util.List;

public interface ISavedPostsRepo {
    void save(Integer postId, Integer viewerId);
    List<Post> get(Integer viewerId);
    int unSavePost(Integer postId, Integer viewerId);
}
