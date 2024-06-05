package code.space.codespace.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UploadMapper {
    @Select("select max(algo_id) from algorithm")
    Integer max_algo_id();

    @Select("select user_id from user where username='${username}'")
    Integer find_author(String username);

    @Select("insert into algorithm(algo_id, title, content, author, `desc`, line, language) " +
            "value (${algo_id}, '${title}', '${content}', ${author_id}, '${desc}', ${line}, '${language}')")
    Integer upload(int algo_id, String title, String content, int author_id, String desc, int line, String language);

    @Select("insert into algorithm_tag(algo_id, tag) value (${algo_id}, '${tag}')")
    Integer upload_tag(int algo_id, String tag);
}
