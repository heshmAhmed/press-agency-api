package api.press.util;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Objects;

public abstract class QueryUtil {
    /**
     *
     * @param con
     * @param statement
     * @param values
     * @param <T>
     * @return preparedStatement
     * @throws SQLException
     */
    private static <T> PreparedStatement getInsertStat(Connection con, String statement, List<T> values) throws SQLException {
        PreparedStatement ps = con.prepareStatement(statement, Statement.RETURN_GENERATED_KEYS);
        int i = 1;
        for (T value : values) {
            ps.setObject(i++, value);
        }
        return ps;
    }

    /**
     *
     * @param jdbcTemplate
     * @param statement
     * @param values
     * @param <T>
     * @return generated id of inserted row
     * @throws Exception
     */
    public static <T> Integer insertRow(JdbcTemplate jdbcTemplate, String statement, List<T> values) throws Exception{
        GeneratedKeyHolder holder = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> getInsertStat(con, statement, values), holder);
        return Objects.requireNonNull(holder.getKey()).intValue();
    }
}
