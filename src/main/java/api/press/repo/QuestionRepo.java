package api.press.repo;

import api.press.model.Question;
import api.press.repo.IRepo.IQuestionRepo;
import api.press.util.QueryUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.Collections;

@Repository
@RequiredArgsConstructor
public class QuestionRepo implements IQuestionRepo {
    private final JdbcTemplate jdbcTemplate;

    @Override
    public Integer insert(Question question) {
        String statement = "INSERT INTO question" +
                "(post_id, viewer_id, body, create_date, viewer_name)" +
                " VALUES (?,?,?,?,?)";

        ArrayList<? super Object> values = new ArrayList<>();
        Collections.addAll(values, question.getPostId(), question.getViewerId(), question.getBody()
        , question.getCreateDate(), question.getViewerName());

        return QueryUtil.insertRow(jdbcTemplate, statement, values);
    }
}
