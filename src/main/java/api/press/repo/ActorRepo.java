package api.press.repo;

import api.press.model.*;
import api.press.util.QueryUtil;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;


@Repository
public class ActorRepo{
    private final JdbcTemplate jdbcTemplate;

    public ActorRepo(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Optional<Actor> findByEmail(String email){
        Optional<Actor> optionalActor;
        try {
            optionalActor = Optional.ofNullable(jdbcTemplate.queryForObject(
                    "select * from actor where email = ?",
                    ActorFactory::createActor, email
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
                    ActorFactory::createActor, username
            ));
        }catch (Exception e){
            actorOptional = Optional.empty();
        }
        return actorOptional;
    }

    public Optional<Actor> insert(Actor actor){
        Optional<Actor> actorOptional;
        String statement = "INSERT INTO actor(first_name, last_name, email, password, phone, photo, username, role_id)" +
                " VALUES (?,?,?,?,?,?,?,?) ";
        ArrayList<? super Object> values = new ArrayList<>();
        Collections.addAll(values, actor.getFirstname(), actor.getLastname(), actor.getEmail(), actor.getPassword(), actor.getPhone()
        ,actor.getPhoto(), actor.getUsername(), actor.getRole().id);
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

    public Role getActorRole(int role_id){
        Role role = Role.valueOf(
                jdbcTemplate.queryForObject("select role from role where id = " +role_id,String.class));
        return role;
    }

//
//    public void insertRoles(){
//        for (Role role:
//             Role.values()) {
//            try {
//                jdbcTemplate.execute("insert into role (role) values ('" + role.toString() + "')");
//            }catch (Exception e){
//                System.out.println("Role is already exists");
//            }
//        }
////    }
//
//    public void setRoleIds() {
//        for (Role role: Role.values()) {
//            try {
//                role.id = jdbcTemplate.
//                        queryForObject("select id from role where role = '"+role.name()+"'", Integer.class);
//            }catch (Exception e){
//                System.out.println("Role is not found");
//            }
//        }
//    }
}
