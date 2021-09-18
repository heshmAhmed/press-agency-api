package api.press.repo;

import api.press.repo.IRepo.ISavedPostsRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.Date;

@RequiredArgsConstructor
@Repository
public class SavedPostsRepo implements ISavedPostsRepo {
    private final JdbcTemplate jdbcTemplate;

    @Override
    public void save(Integer postId, Integer viewerId) {
        String query = "insert into saved_post(viewer_id, post_id, create_date) values(?, ?, ?)";
        this.jdbcTemplate.update(query, viewerId, postId, new Date());
    }
}
