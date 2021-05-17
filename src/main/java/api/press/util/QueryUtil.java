package api.press.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class QueryUtil {
    public static <T> PreparedStatement getInsertStat(Connection con, String statement, List<T> values) throws SQLException {
        PreparedStatement ps = con.prepareStatement(statement, Statement.RETURN_GENERATED_KEYS);
        int i = 1;
        for(T value: values){
            ps.setObject(i++, value);
        }
        return ps;
    }
}
