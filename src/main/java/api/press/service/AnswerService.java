package api.press.service;

import api.press.model.Answer;
import api.press.repo.IRepo.IAnswerRepo;
import api.press.util.TokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AnswerService {
    private final IAnswerRepo answerRepo;
    private final TokenUtil tokenUtil;

    public Answer addAnswer(Integer questionId,Answer answer) throws RuntimeException{
        answer.setQuestionId(questionId);
        answer.setActorId(tokenUtil.getCurrentWebToken().getId());
        answer.setActorName(tokenUtil.getCurrentWebToken().getUsername());
        try{
            answer.setId(answerRepo.add(answer));
        }catch (DataIntegrityViolationException e){
            throw new RuntimeException("Question not found or bad content!");
        }
        return answer;
    }

    public void updateAnswer(Answer answer) throws RuntimeException{
        answer.setActorId(tokenUtil.getCurrentWebToken().getId());
        int rs;
        try {
            rs = answerRepo.update(answer);
        }catch (DataIntegrityViolationException e){
            throw new RuntimeException("Bad body content!");
        }

        if(rs == 0)
            throw new RuntimeException("Answer not found!");
    }
}
