package api.press.repo;

import api.press.model.Post;
import api.press.repo.IRepo.IPostRepo;
import api.press.util.QueryUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.*;

@Slf4j
@Repository
public class PostRepo implements IPostRepo {
    private final JdbcTemplate jdbcTemplate;

    public PostRepo(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Optional<Post> insert(Post post){
        Optional<Post> optionalPost;
        String statement = "INSERT INTO post(editor_id, editor_name, title, body, no_views, no_likes, " +
                            "no_dislikes, create_date, state, type_id)" +
                            " VALUES (?,?,?,?,?,?,?,?,?,?)";

        ArrayList<? super Object> values = new ArrayList<>();
        Collections.addAll(values, post.getEditor().getId(), post.getEditor().getUsername(), post.getTitle(), post.getBody(), post.getNoViews(),
                post.getNoLikes(), post.getNoDislikes(), post.getCreateDate(), post.isState(), post.getPostType().getId());
       try {
           post.setId(QueryUtil.insertRow(jdbcTemplate, statement, values));
           optionalPost = Optional.of(post);
       }catch (DataIntegrityViolationException e){
           optionalPost = Optional.empty();
       }
        return optionalPost;
    }

//    public Integer like(Integer postId, Integer viewerId){
//    public void insertPostTypes(){
//        for (PostType type:
//                PostType.values()) {
//            try {
//                jdbcTemplate.update("insert into post_type (type) values ('" + type.toString() + "')");
//            }catch (Exception e){
//
//            }
//        }
//    }

//    public void setPostTypesIds(){
//        for (PostType type: PostType.values()) {
//            try {
//                type.id = jdbcTemplate.
//                        queryForObject("select id from post_type where type = '" + type.name() + "'", Integer.class);
//            }catch (Exception e){
//                System.out.println("Type is not found");
//            }
//        }
//    }
}
