package code.space.codespace.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    String username;
    String avatar;
    String email;
    Integer repo;
    Integer score;
    Integer line;
}
