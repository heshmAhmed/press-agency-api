package api.press.repo;

import api.press.model.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class ActorFactory {

    @Autowired
    private static ActorRepo actorRepo;

    public static Actor createActor(ResultSet rs, int numRow) throws SQLException {
        Actor actor;
        String role = rs.getString("role");
        if(role.equals(Role.ADMIN.toString()))
            actor = new Admin();
        else if (role.equals(Role.EDITOR.toString()))
            actor = new Editor();
        else if (role.equals(Role.VIEWER.toString()))
            actor = new Viewer();
        else
            throw new IllegalStateException("Unexpected value: " + rs.getString("role"));
        mapRowToActor(rs, actor);
        return actor;
    }

    private static void mapRowToActor(ResultSet rs,Actor actor) throws SQLException {
        actor.setId(rs.getInt("id"));
        actor.setFirstname(rs.getString("first_name"));
        actor.setLastname(rs.getString("last_name"));
        actor.setEmail(rs.getString("email"));
        actor.setPassword(rs.getString("password"));
        actor.setPhone(rs.getString("phone"));
        actor.setPhoto(rs.getString("photo"));
        actor.setRole(actorRepo.getActorRole(rs.getInt("role_id")));
    }


}
