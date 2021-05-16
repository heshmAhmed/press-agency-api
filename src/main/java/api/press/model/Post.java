package api.press.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Post {
    private Integer id;
    private Actor editor;
    private String title;
    private String body;
    private Integer no_views;
    private Integer no_likes;
    private Integer no_dislikes;
    private Date create_date;
    private boolean state;
    private PostType postType;
}
