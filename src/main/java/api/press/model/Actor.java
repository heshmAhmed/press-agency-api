package api.press.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Actor {
    private Integer id;
    private String firstname;
    private String lastname;
    private String email;
    private String phone;
    private String password;
    private String username;
    private byte[] photo;
    private List<Post> posts;
    private String role;
}
