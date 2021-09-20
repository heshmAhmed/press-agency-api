package api.press.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Question {
    private Integer id;
    private Integer postId;
    private Integer viewerId;
    private String viewerName;
    private String body;
    private Date createDate;
    private List<Answer> answers;
}
