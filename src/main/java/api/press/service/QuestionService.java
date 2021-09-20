package api.press.service;

import api.press.model.Question;
import api.press.repo.IRepo.IQuestionRepo;
import api.press.util.TokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class QuestionService {
    private final IQuestionRepo questionRepo;
    private final TokenUtil tokenUtil;

    public Question addQuestion(Question question, Integer postId) throws RuntimeException{
        question.setViewerId(tokenUtil.getCurrentWebToken().getId());
        question.setPostId(postId);
        question.setViewerName(tokenUtil.getCurrentWebToken().getUsername());
        try {
            question.setId(questionRepo.insert(question));
        }catch (DataIntegrityViolationException e){
            throw new RuntimeException("Bad body content!");
        }
        return question;
    }
}
