package api.press.repo.IRepo;

import api.press.model.Post;

import java.util.Optional;

public interface IPostRepo {
    Optional<Post> insert(Post post);
}
