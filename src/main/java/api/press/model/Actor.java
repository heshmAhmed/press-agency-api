package api.press.model;
import lombok.Data;


@Data
public abstract class Actor {
    protected Integer id;
    protected String firstname;
    protected String lastname;
    protected String username;
    protected String email;
    protected String phone;
    protected String password;
    protected String photo;
    protected Role role;
}
