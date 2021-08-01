package api.press.repo;

import api.press.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class ActorMapper implements RowMapper<Actor> {

    private final JdbcTemplate jdbcTemplate;

    public ActorMapper(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Actor mapRow(ResultSet rs, int i) throws SQLException {
        Actor actor = createActor(rs.getInt("role_id"));
        actor.setId(rs.getInt("id"));
        actor.setFirstname(rs.getString("first_name"));
        actor.setLastname(rs.getString("last_name"));
        actor.setEmail(rs.getString("email"));
        actor.setUsername(rs.getString("username"));
        actor.setPassword(rs.getString("password"));
        actor.setPhone(rs.getString("phone"));
        actor.setPhoto(rs.getString("photo"));
        return actor;
    }

    private Actor createActor(int role_id){
        Role role = getActorRole(role_id);
        Actor actor;
        if(role.equals(Role.ADMIN))
            actor = new Admin();
        else if (role.equals(Role.EDITOR))
            actor = new Editor();
        else if (role.equals(Role.VIEWER))
            actor = new Viewer();
        else
            throw new IllegalStateException("Unexpected value: " + role_id);
        actor.setRole(role);
        return actor;
    }

    public Role getActorRole(int role_id)
    {
        Role role = Role.valueOf(
                jdbcTemplate.queryForObject("select role from role where id = " + role_id, String.class));
        role.id = role_id;
        return role;
    }



}
