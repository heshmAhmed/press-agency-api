package api.press.mapper;

import api.press.model.ActorType;
import api.press.model.Editor;
import api.press.model.Post;
import api.press.model.Role;
import api.press.repo.IRepo.IPostTypeRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@RequiredArgsConstructor
@Component
public class PostMapper implements RowMapper<Post> {
    private final IPostTypeRepo postTypeRepo;

    @Override
    public Post mapRow(ResultSet rs, int i) throws SQLException {
        return Post.builder().id(rs.getInt("id"))
                .editorId(rs.getInt("editor_id"))
                .editorName(rs.getString("editor_name"))
                .title(rs.getString("title"))
                .body(rs.getString("body"))
                .noViews(rs.getInt("no_views"))
                .noLikes(rs.getInt("no_likes"))
                .noDislikes(rs.getInt("no_dislikes"))
                .createDate(rs.getDate("create_date"))
                .state(rs.getBoolean("state"))
                .postType(postTypeRepo.getPostType(rs.getInt("type_id")))
                .build();
    }
}
