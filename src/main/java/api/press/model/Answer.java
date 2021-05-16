package api.press.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Answer {
    private Integer id;
    private Question question;
    private String body;
    private Date create_date;
}
