package api.press.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
public class WebToken {
    private String username;
    private Integer id;
    private Date exp_date;
    private Date create_date;
    private List<SimpleGrantedAuthority> authority;
}
