package api.press.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Answer {
    private int id;
    private Question question;
    private String body;
    private Date create_date;
}
