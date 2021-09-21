package api.press.repo.IRepo;

import api.press.model.Question;

public interface IQuestionRepo {
    Integer insert(Question question);
    int update(Question question);
    int delete(Integer postId, Integer questionId, Integer viewerId);
}
