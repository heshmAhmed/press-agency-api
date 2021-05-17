package api.press.repo;

import api.press.model.Actor;
import api.press.model.Post;
import api.press.util.QueryUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PostRepo {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public <T> Optional<Post> insert(Post post){
        Optional<Post> postOptional;
        String statement = "INSERT INTO post(editor_id, editor_name, title, body, no_views, no_likes, " +
                            "no_dislikes, create_date, state, type)" +
                            " VALUES (?,?,?,?,?,?,?,?,?,?)";
        try {
                GeneratedKeyHolder holder = new GeneratedKeyHolder();
                jdbcTemplate.update(con -> {
                    return QueryUtil.getInsertStat(con, statement, List.of(post.getEditorID(), post.getEditorName(), post.getTitle(), post.getBody(), post.getNo_views(),
                            post.getNo_likes(), post.getNo_dislikes(), post.getCreate_date(), post.isState(), post.getPostType().label));
                    }, holder);
                post.setId(holder.getKey().intValue());
                postOptional = Optional.of(post);
            }catch (Exception e){
                postOptional = Optional.empty();
            }
            return postOptional;
    }
}
