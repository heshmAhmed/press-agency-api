package api.press.repo;

import api.press.model.*;
import api.press.repo.IRepo.IActorRepo;
import api.press.mapper.ActorMapper;
import api.press.util.QueryUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ActorRepo implements IActorRepo {
    private final JdbcTemplate jdbcTemplate;
    private final ActorMapper actorFactory;

    @Override
    public Optional<Actor> findByEmail(String email){
        Optional<Actor> optionalActor;
        try {
            optionalActor = Optional.ofNullable(jdbcTemplate.queryForObject(
                    "select * from actor where email = ?",
                    actorFactory, email
            ));

        }catch (Exception e){
            optionalActor = Optional.empty();
        }
        return optionalActor;
    }

    @Override
    public Optional<Actor> findByUsername(String username){
        Optional<Actor> actorOptional;
        try {
            actorOptional = Optional.ofNullable(jdbcTemplate.queryForObject(
                    "select * from actor where username = ?",
                    actorFactory, username
            ));
        }catch (Exception e){
            actorOptional = Optional.empty();
        }
        return actorOptional;
    }

    @Override
    public Optional<Actor> findByID(int id){
        Optional<Actor> actorOptional;
        try {
            actorOptional = Optional.ofNullable(jdbcTemplate.queryForObject(
                    "select * from actor where id = ?",
                    actorFactory, id
            ));
        }catch (Exception e){
            actorOptional = Optional.empty();
        }
        return actorOptional;
    }

    @Override
    public Optional<Actor> insert(Actor actor){
        Optional<Actor> actorOptional;
        String statement = "INSERT INTO actor(first_name, last_name, email, password, phone, photo, username, role_id)" +
                " VALUES (?,?,?,?,?,?,?,?) ";
        ArrayList<? super Object> values = new ArrayList<>();
        Collections.addAll(values, actor.getFirstname(), actor.getLastname(), actor.getEmail(), actor.getPassword(), actor.getPhone()
        ,actor.getPhoto(), actor.getUsername(), actor.getRole().getId());
        try {
            actor.setId(QueryUtil.insertRow(jdbcTemplate, statement, values));
            actorOptional = Optional.of(actor);
        }catch (DuplicateKeyException e){
            actorOptional = Optional.empty();
        }
        return actorOptional;
    }

    @Override
    public int update(Actor actor){
        String query = "update actor set first_name = ?, last_name = ?, email = ?, phone = ?, photo = ?, role_id = ? where id = ?";
        return jdbcTemplate.update(query, actor.getFirstname(), actor.getLastname(), actor.getEmail(), actor.getPhone(),
                actor.getPhoto(), actor.getRole().getId(),actor.getId());
    }

    @Override
    public List<Actor> getAllActors(){
        return this.jdbcTemplate.query("SELECT * FROM actor", actorFactory);
    }

    @Override
    public int delete(Integer actorId) {
        return this.jdbcTemplate.update("delete from actor where id = " + actorId);
    }

}
