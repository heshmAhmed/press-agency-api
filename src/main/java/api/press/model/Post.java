package api.press.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Post {
    private Integer id;
    private Editor editor;
    private String title;
    private String body;
    private Integer noViews;
    private Integer noLikes;
    private Integer noDislikes;
    private Date createDate;
    private boolean state;
    private PostType postType;
    private List<Question> questions;
}
