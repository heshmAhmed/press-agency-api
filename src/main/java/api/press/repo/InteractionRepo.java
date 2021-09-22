package api.press.repo;

import api.press.model.Interaction;
import api.press.repo.IRepo.IInteractionRepo;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


@AllArgsConstructor
@Repository
public class InteractionRepo implements IInteractionRepo {
    private final JdbcTemplate jdbcTemplate;

    @Override
    public int add(Interaction interaction) {
        String query = "insert interaction (viewer_id, post_id, is_like)" +
                        " VALUES (?,?,?) ON DUPLICATE KEY UPDATE is_like = ? ";
        return jdbcTemplate.update(query,
                interaction.getViewerId(),
                interaction.getPostId(),
                interaction.getIsLike(),
                interaction.getIsLike());
    }

    @Override
    public int delete(Integer postId, Integer id) {
        return jdbcTemplate.update("delete from interaction where post_id = ? and viewer_id = ?",
                postId, id);
    }
}