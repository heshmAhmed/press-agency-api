package api.press.repo;

import api.press.model.Actor;
import api.press.util.QueryUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.*;

@Repository
public class ActorRepo{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Optional<Actor> findByEmail(String email){
        Optional<Actor> optionalActor;
        try {
            optionalActor = Optional.ofNullable(jdbcTemplate.queryForObject(
                    "select * from actor where email = ?",
                    this::mapRowToActor,email
            ));

        }catch (Exception e){
            optionalActor = Optional.empty();
        }
        return optionalActor;
    }

    public Optional<Actor> findByUsername(String username){
        Optional<Actor> actorOptional;
        try {
            actorOptional = Optional.ofNullable(jdbcTemplate.queryForObject(
                    "select * from actor where username = ?",
                    this::mapRowToActor, username
            ));
        }catch (Exception e){
            actorOptional = Optional.empty();
        }
        return actorOptional;
    }

    public Optional<Actor> insert(Actor actor){
        Optional<Actor> actorOptional;
        String statement = "INSERT INTO actor(first_name, last_name, email, password, phone, photo, username, role)" +
                " VALUES (?,?,?,?,?,?,?,?) ";
        ArrayList<? super Object> values = new ArrayList<>();
        Collections.addAll(values, actor.getFirstname(), actor.getLastname(), actor.getEmail(), actor.getPassword(), actor.getPhone()
        ,actor.getPhoto(), actor.getUsername(), actor.getRole());
        try {
            actor.setId(QueryUtil.insertRow(jdbcTemplate, statement, values));
            actorOptional = Optional.of(actor);
        }catch (DuplicateKeyException e){
            actorOptional = Optional.empty();
        }
        return actorOptional;
    }

    public Optional<Actor> update(Actor actor){
        String query = "update actor set first_name = ?, last_name = ?, email = ?, phone = ?, photo = ?, role = ? where id = ?";
        int rs = jdbcTemplate.update(query, actor.getFirstname(), actor.getLastname(), actor.getEmail(), actor.getPhone(),
                actor.getPhoto(), actor.getRole(),actor.getId());
        return rs == 1 ? Optional.of(actor) : Optional.empty();
    }

    private Actor mapRowToActor(ResultSet rs, int rowNum) throws SQLException{
        return new Actor(
                rs.getInt("id"),
                rs.getString("first_name"),
                rs.getString("last_name"),
                rs.getString("email"),
                rs.getString("phone"),
                rs.getString("password"),
                rs.getString("username"),
                rs.getBytes("photo"),
                null,
                rs.getString("role")
        );
    }
}
