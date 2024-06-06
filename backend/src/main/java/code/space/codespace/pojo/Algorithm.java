package code.space.codespace.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Algorithm {
    Integer algoId;
    String title;
    String author;
    String content;
    Integer line;
    String language;
    String desc;
    String[] tags={};
}
