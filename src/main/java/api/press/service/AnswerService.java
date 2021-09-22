package api.press.service;

import api.press.model.Answer;
import api.press.repo.IRepo.IAnswerRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AnswerService {
    private final IAnswerRepo answerRepo;

    public Answer addAnswer(Integer questionId,Answer answer){
        answer.setQuestionId(questionId);
        try{
            answer.setId(answerRepo.add(answer));
        }catch (DataIntegrityViolationException e){
            throw new RuntimeException("Question not found or bad content!");
        }
        return answer;
    }
}
