package api.press.repo;

import api.press.model.PostType;
import api.press.repo.IRepo.IPostTypeRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class PostTypeRepo implements IPostTypeRepo {
    private final JdbcTemplate jdbcTemplate;

    @Override
    public PostType getPostType(int id) {
        return new PostType(jdbcTemplate.queryForObject("select type from post_type where id = " + id, String.class),
                id);
    }
}
