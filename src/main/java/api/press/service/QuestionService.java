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

    public void updateQuestion(Question question, Integer postId, Integer questionId){
        question.setId(questionId);
        question.setPostId(postId);
        question.setViewerId(tokenUtil.getCurrentWebToken().getId());
        int rs;
        try{
            rs = questionRepo.update(question);
        }catch (DataIntegrityViolationException e){
            throw new RuntimeException("Bad body content!");
        }

        if(rs == 0)
            throw new RuntimeException("Question not found!");
    }

    public void deleteQuestion(Integer postId, Integer questionId) throws RuntimeException {
        int rs;
        rs = questionRepo.delete(postId, questionId, tokenUtil.getCurrentWebToken().getId());
       if(rs == 0)
           throw new RuntimeException("Question not found!");
    }
}
