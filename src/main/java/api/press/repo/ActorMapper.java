package api.press.repo;

import api.press.model.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class ActorMapper implements RowMapper<Actor> {

    private final RoleRepo roleRepo;

    public ActorMapper(RoleRepo roleRepo) {
        this.roleRepo = roleRepo;
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
        Role role = roleRepo.getActorRole(role_id);
        Actor actor;
        if(role.getName().equals(ActorType.ADMIN.name()))
            actor = new Admin();
        else if (role.getName().equals(ActorType.EDITOR.name()))
            actor = new Editor();
        else if (role.getName().equals(ActorType.VIEWER.name()))
            actor = new Viewer();
        else
            throw new IllegalStateException("Unexpected value: " + role_id);
        actor.setRole(role);
        return actor;
    }





}
