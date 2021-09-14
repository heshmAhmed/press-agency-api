package api.press.repo;

import api.press.model.Interaction;
import api.press.repo.IRepo.IInteractionRepo;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import java.util.Optional;

@AllArgsConstructor
public class InteractionRepo implements IInteractionRepo {
    private final JdbcTemplate jdbcTemplate;

    @Override
    public Optional<Interaction> addLikeInteraction(Integer postId, Integer userId) {
        isInteractionExist(postId, userId).ifPresentOrElse((Interaction interaction) ->{
            // edit the interaction save it to the database
        },()-> {
            // add new interaction to the database
        });
        return Optional.empty();
    }

    @Override
    public Optional<Interaction> addDisLikeInteraction(Integer postId, Integer userId) {
        isInteractionExist(postId, userId).ifPresentOrElse((Interaction interaction) ->{
            // edit the interaction save it to the database
        }, () -> {
            // add new interaction to the database
        });
        return Optional.empty();
    }

    @Override
    public Optional<Interaction> isInteractionExist(Integer postId, Integer userId) {
        return Optional.empty();
    }
}
