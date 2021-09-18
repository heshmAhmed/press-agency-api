package api.press.repo.IRepo;

public interface ISavedPostsRepo {
    void save(Integer postId, Integer viewerId);
}
