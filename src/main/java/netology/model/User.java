package netology.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.netology.authorities.Authorities;

import java.util.List;

@Data
@AllArgsConstructor
public class User {

    private String login;
    private String password;
    private List<Authorities> authorities;
}