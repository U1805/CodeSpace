package code.space.codespace.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Algorithm {
    Integer id;
    String title;
    String author;
    String content;
    Integer line;
    String language;
    String desc;
    Boolean origin;
    List<String> tags;
}
