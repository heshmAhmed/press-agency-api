package api.press.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class Actor {
    private Integer id;
    private String firstname;
    private String lastname;
    private String username;
    private String email;
    private String phone;
    private String password;
    private String photo;
    private Role role;
}
