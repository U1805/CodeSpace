package code.space.codespace.mapper;

import code.space.codespace.pojo.Algorithm;
import org.apache.ibatis.annotations.*;
import java.util.List;

@Mapper
public interface AlgoMapper {
    @Select("SELECT user_id FROM user WHERE username = #{username}")
    Integer findAuthor(String username);

    @Insert("INSERT INTO algorithm (title, content, author, `desc`, line, language, origin, create_time, update_time) " +
            "VALUES (#{title}, #{content}, #{author_id}, #{desc}, #{line}, #{language}, #{origin}, NOW(), NOW())")
    void upload(@Param("title") String title, @Param("content") String content, @Param("author_id") int authorId,
                @Param("desc") String desc, @Param("line") int line, @Param("language") String language,
                @Param("origin") boolean origin);

    @Select("SELECT LAST_INSERT_ID()")
    Integer getLastInsertId();

    @Insert("INSERT INTO algorithm_tag (algo_id, tag) VALUES (#{algo_id}, #{tag})")
    Integer uploadTag(@Param("algo_id") int algoId, @Param("tag") String tag);


    List<Algorithm> searchByKeyword(String keyword, String username);

    @Select("select count(*) from algorithm where algo_id = #{id}")
    Integer searchById(Integer id);

    @Delete("delete from algorithm where algo_id = #{id}")
    void delete(Integer id);
}
