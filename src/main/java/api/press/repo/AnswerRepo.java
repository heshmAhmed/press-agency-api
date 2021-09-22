package api.press.repo;

import api.press.model.Answer;
import api.press.repo.IRepo.IAnswerRepo;
import api.press.util.QueryUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;


@RequiredArgsConstructor
@Repository
public class AnswerRepo implements IAnswerRepo {
    private final JdbcTemplate jdbcTemplate;

    @Override
    public Integer add(Answer answer) {
        String query = "insert into answer (question_id, actor_id, actor_name, body, create_date) values(?, ?, ?, ?, ?)";
        ArrayList<? super Object> values = new ArrayList<>();
        Collections.addAll(values, answer.getQuestionId(), answer.getActorId(), answer.getActorName(), answer.getBody(), answer.getCreateDate());
        return QueryUtil.insertRow(jdbcTemplate, query, values);
    }

    @Override
    public int update(Answer answer) {
        return jdbcTemplate.update("update answer set body = ? where id = ? and actor_id = ?",
                answer.getBody(),
                answer.getId(),
                answer.getActorId());
    }

    @Override
    public int delete(Integer id, Integer actorId) {
        return jdbcTemplate.update("delete from answer where id = ? and actor_id = ?",
                id, actorId);
    }
}
