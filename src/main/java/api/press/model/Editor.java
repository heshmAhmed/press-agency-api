package api.press.model;

import lombok.*;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
public class Editor extends Actor{
    private List<Post> posts;
    private List<Answer> answers;
}
