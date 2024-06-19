package code.space.codespace.mapper;

import code.space.codespace.pojo.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {
    @Select("SELECT u.username, u.score, u.avatar, u.email, " +
            "COUNT(a.algo_id) AS repo, " +
            "COALESCE(SUM(a.line), 0) AS line " +
            "FROM user u " +
            "LEFT JOIN algorithm a ON u.user_id = a.author " +
            "WHERE u.username = #{username} " +
            "GROUP BY u.username, u.score, u.avatar, u.email")
    @Results({
            @Result(property = "username", column = "username"),
            @Result(property = "score", column = "score"),
            @Result(property = "avatar", column = "avatar"),
            @Result(property = "email", column = "email"),
            @Result(property = "repo", column = "repo"),
            @Result(property = "line", column = "line")
    })
    User getUserInfo(String username);

    @Select("SELECT u.username, u.score, u.avatar, u.email, " +
            "COUNT(a.algo_id) AS repo, " +
            "COALESCE(SUM(a.line), 0) AS line " +
            "FROM user u " +
            "LEFT JOIN algorithm a ON u.user_id = a.author " +
            "GROUP BY u.user_id, u.username, u.score, u.avatar, u.email")
    @Results({
            @Result(property = "username", column = "username"),
            @Result(property = "score", column = "score"),
            @Result(property = "avatar", column = "avatar"),
            @Result(property = "email", column = "email"),
            @Result(property = "repo", column = "repo"),
            @Result(property = "line", column = "line")
    })
    List<User> getList();

    @Select("select count(*) from user where username = #{username} and password = #{password} and admin = #{admin}")
    Integer login(String username, String password, int admin);

    @Select("select count(*) from user where username=#{username}")
    Integer find_username(String username);

    @Insert("insert into user(username, password, avatar) value(#{username}, #{password}, #{avatar})")
    Integer register(String username, String password, String avatar);

    @Update("update user set score = score+#{score} where username = #{author}")
    void updateScore(String author, int score);

    @Update("update user set avatar = #{avatar}, email = #{email} where username = #{username}")
    void setInfo(User user);

    @Delete("delete from user where username = #{username}")
    void delete(String username);
}
