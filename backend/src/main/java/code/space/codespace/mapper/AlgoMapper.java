package code.space.codespace.mapper;

import code.space.codespace.pojo.Algorithm;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface AlgoMapper {
    @Select("select algo_id, title, author, content, line, language, `desc` from algorithm where author=${author}")
    Algorithm[] search_1(Integer author); //keyword==null

    @Select("select tag from algorithm_tag where algo_id=${algo_id}")
    String[] search_tag(Integer algo_id);

    @Select("select algo_id, title, author, content, line, language, `desc` from algorithm where algo_id=${algo_id}")
    Algorithm search_2(Integer algo_id); //author==null

    @Select("select user_id from user where username='${username}'")
    Integer find_author(String username);

    @Select("select algo_id from algorithm_tag where tag='${tag}'")
    Integer[] search_algo_id(String tag);

    @Select("select max(algo_id) from algorithm")
    Integer max_algo_id();

    @Select("insert into algorithm(algo_id, title, content, author, `desc`, line, language) " +
            "value (${algo_id}, '${title}', '${content}', ${author_id}, '${desc}', ${line}, '${language}')")
    Integer upload(int algo_id, String title, String content, int author_id, String desc, int line, String language);

    @Select("insert into algorithm_tag(algo_id, tag) value (${algo_id}, '${tag}')")
    Integer upload_tag(int algo_id, String tag);
}
