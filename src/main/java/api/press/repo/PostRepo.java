package api.press.repo;

import api.press.model.Post;
import api.press.util.QueryUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class PostRepo {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Optional<Post> insert(Post post){
        Optional<Post> postOptional;
        String statement = "INSERT INTO post(editor_id, editor_name, title, body, no_views, no_likes, " +
                            "no_dislikes, create_date, state, type)" +
                            " VALUES (?,?,?,?,?,?,?,?,?,?)";

        ArrayList<? super Object> values = new ArrayList<>();
        Collections.addAll(values, post.getEditorID(), post.getEditorName(), post.getTitle(), post.getBody(), post.getNo_views(),
                post.getNo_likes(), post.getNo_dislikes(), post.getCreate_date(), post.isState(), post.getPostType().label);

        try {
            post.setId(QueryUtil.insertRow(jdbcTemplate, statement, values));
            postOptional = Optional.of(post);
        } catch (Exception e) {
            e.printStackTrace();
            postOptional = Optional.empty();
        }
        return postOptional;
    }
}
