package code.space.codespace.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Algorithm {
    private Integer id;
    private String title;
    private String author;
    private String content;
    private Integer line;
    private String language;
    private String desc;
    private Boolean origin;
    private List<String> tags;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
