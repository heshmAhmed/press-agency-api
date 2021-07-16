package api.press.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Interaction {
    private Integer id;
    private Viewer viewer;
    private Post post;
    private boolean is_like;
}
