package api.press.repo;

import api.press.model.Answer;
import api.press.repo.IRepo.IAnswerRepo;
import api.press.util.QueryUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;


@RequiredArgsConstructor
@Repository
public class AnswerRepo implements IAnswerRepo {
    private final JdbcTemplate jdbcTemplate;

    @Override
    public Integer add(Answer answer) {
        String query = "insert into answer (question_id, body, create_date) values(?, ?, ?)";
        ArrayList<? super Object> values = new ArrayList<>();
        Collections.addAll(values, answer.getQuestionId(), answer.getBody(), answer.getCreateDate());
        return QueryUtil.insertRow(jdbcTemplate, query, values);
    }
}
