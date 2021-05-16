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
    private Post post;
    private Actor viewer;
    private String body;
    private Date create_date;
    private List<Answer> answers;
}
