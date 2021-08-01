package api.press.model;

import lombok.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
public class Viewer extends Actor{
    private List<Question> questions;
    private List<Interaction> interactions;
}
