package api.press.repo;

import api.press.model.ActorType;
import api.press.model.Role;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class RoleRepo implements IRole {

    private final JdbcTemplate jdbcTemplate;
    public RoleRepo(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Role getActorRole(int role_id)
    {
        return new Role(jdbcTemplate.queryForObject("select role from role where id = " + role_id, String.class), role_id);

    }

    public int getRoleId(String name)
    {
        return jdbcTemplate.queryForObject("select id from role where role = " + name, Integer.class);
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
