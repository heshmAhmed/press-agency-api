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
     * @param con Database connection
     * @param statement Sql statement
     * @param values List of values
     * @param <T>
     * @return PreparedStatement object
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
     * @param statement Sql statement
     * @param values List of values
     * @param <T>
     * @return ID generated of inserted row in DB
     */
    public static <T> Integer insertRow(JdbcTemplate jdbcTemplate, String statement, List<T> values){
        GeneratedKeyHolder holder = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> getInsertStat(con, statement, values), holder);
        return Objects.requireNonNull(holder.getKey()).intValue();
    }
}
