package api.press.service;

import api.press.model.Interaction;
import api.press.repo.IRepo.IInteractionRepo;
import api.press.util.TokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InteractionService {
    private final IInteractionRepo interactionRepo;
    private final TokenUtil tokenUtil;

    public void addInteraction(Integer postId, Interaction interaction) throws RuntimeException{
        interaction.setPostId(postId);
        interaction.setViewerId(tokenUtil.getCurrentWebToken().getId());
        try {
            interactionRepo.add(interaction);
        }catch (DataIntegrityViolationException e){
            throw new RuntimeException("Bad content");
        }

    }

    public void deleteInteraction(Integer postId) throws RuntimeException{
        int rs = interactionRepo.delete(postId, tokenUtil.getCurrentWebToken().getId());
        if(rs == 0)
            throw new RuntimeException("Interaction not found!");
    }
}
