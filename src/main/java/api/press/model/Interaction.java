package api.press.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Interaction {
    private Integer id;
    private Integer viewerId;
    private Integer postId;
    private Boolean isLike;
}
