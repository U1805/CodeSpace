package code.space.codespace.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UploadInfo {
    String title;
    String content;
    String author;
    Integer line;
    String language;
    String desc;
    Boolean origin;
    String[] tags={};
}
