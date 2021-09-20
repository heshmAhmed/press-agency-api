package api.press.repo;

import api.press.mapper.PostMapper;
import api.press.model.Post;
import api.press.repo.IRepo.ISavedPostsRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class SavedPostsRepo implements ISavedPostsRepo {
    private final JdbcTemplate jdbcTemplate;
    private final PostMapper postMapper;

    @Override
    public void save(Integer postId, Integer viewerId) {
        String query = "insert into saved_post(viewer_id, post_id, create_date) values(?, ?, ?)";
        this.jdbcTemplate.update(query, viewerId, postId, new Date());
    }

    @Override
    public List<Post> get(Integer viewerId) {
        return jdbcTemplate.query("select p.* from actor a join saved_post sp on a.id = sp.viewer_id\n" +
                "join post p on sp.post_id = p.id where viewer_id = " + viewerId, postMapper);
    }
}
