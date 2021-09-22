package api.press.repo.IRepo;

import api.press.model.Answer;

public interface IAnswerRepo {
    Integer add(Answer answer);
    int update(Answer answer);
    int delete(Integer id, Integer actorId);
}
