package code.space.codespace.mapper;

import code.space.codespace.pojo.Algorithm;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface AlgoMapper {
    @Select("select algo_id, title, username as author, content, line, language, `desc` " +
            "from algorithm join user on author=user_id " +
            "where username='${author}'")
    Algorithm[] search_by_author(String author);

    @Select("select DISTINCT algo_id, title, username as author, content, line, language, `desc` " +
            "from algorithm_tag natural join (algorithm join `user` on (author=user_id)) " +
            "where tag like concat('%', '${tag}','%')")
    Algorithm[] search_by_tag(String tag);

    @Select("select algo_id, title, username, content, line, language, `desc` " +
            "from algorithm_tag natural join (algorithm join `user` on (author=user_id)) " +
            "where tag like concat('%', '${keyword}','%') and username = '{author}'")
    Algorithm[] search_by_author_tag(String author, String keyword);

    @Select("select tag from algorithm_tag where algo_id = ${algoId}")
    String[] search_tag(Integer algoId);

    @Select("select user_id from user where username='${username}'")
    Integer find_author(String username);

    @Select("select max(algo_id) from algorithm")
    Integer max_algo_id();

    @Insert("INSERT INTO algorithm (algo_id, title, content, author, `desc`, line, language, origin, create_time, update_time) " +
            "VALUES (#{algo_id}, #{title}, #{content}, #{author_id}, #{desc}, #{line}, #{language}, #{origin}, NOW(), NOW())")
    Integer upload(int algo_id, String title, String content, int author_id, String desc, int line, String language, boolean origin);

    @Select("insert into algorithm_tag(algo_id, tag) value (${algo_id}, '${tag}')")
    Integer upload_tag(int algo_id, String tag);
}
