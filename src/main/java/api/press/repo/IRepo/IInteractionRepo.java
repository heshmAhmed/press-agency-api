package api.press.repo.IRepo;

import api.press.model.Interaction;

public interface IInteractionRepo {
    int add(Interaction interaction);
    int delete(Integer postId, Integer id);
}
