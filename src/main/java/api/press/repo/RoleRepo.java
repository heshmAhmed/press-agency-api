package api.press.repo;

import api.press.model.Role;
import api.press.repo.IRepo.IRoleRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class RoleRepo implements IRoleRepo {
    private final JdbcTemplate jdbcTemplate;

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
