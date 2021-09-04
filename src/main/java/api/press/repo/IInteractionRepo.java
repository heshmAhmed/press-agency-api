package api.press.repo;

import api.press.model.Interaction;

import java.util.Optional;

public interface IInteractionRepo {
    Optional<Interaction> addLikeInteraction(Integer postId, Integer userId);
    Optional<Interaction> addDisLikeInteraction(Integer postId, Integer userId);
    Optional<Interaction> isInteractionExist(Integer postId, Integer userId);
}
