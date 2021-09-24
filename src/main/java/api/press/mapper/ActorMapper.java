package api.press.mapper;

import api.press.model.*;
import api.press.repo.RoleRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
@RequiredArgsConstructor
public class ActorMapper implements RowMapper<Actor> {
    private final RoleRepo roleRepo;

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
        if(role.getName().equalsIgnoreCase(ActorType.ADMIN.name()))
            actor = new Admin();
        else if (role.getName().equalsIgnoreCase(ActorType.EDITOR.name()))
            actor = new Editor();
        else if (role.getName().equalsIgnoreCase(ActorType.VIEWER.name()))
            actor = new Viewer();
        else
            throw new IllegalStateException("Unexpected role: " + role);
        actor.setRole(role);
        return actor;
    }


}
