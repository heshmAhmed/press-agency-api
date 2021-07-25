package api.press.model;

import lombok.*;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Editor extends Actor{
    public List<Post> posts;
    public List<Answer> answers;
}
