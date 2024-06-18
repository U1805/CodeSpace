package code.space.codespace.mapper;

import code.space.codespace.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserMapper {

    @Select("select sum(line) from algorithm join user on(author=user_id) where username=#{username}")
    Integer count_line(String username);

    @Select("select count(*) from algorithm join user on(author=user_id) where username=#{username}")
    Integer count_algo(String username);

    @Select("select username, score, avatar, email from user where username = #{username}")
    User search(String username);

    @Select("select count(*) from user where username = #{username} and password = #{password} and admin = #{admin}")
    Integer login(String username, String password, int admin);

    @Select("select count(*) from user where username=#{username}")
    Integer find_username(String username);

    @Insert("insert into user(username, password, avatar) value(#{username}, #{password}, #{avatar})")
    Integer register(String username, String password, String avatar);

    @Update("update user set score = score+#{score} where username = #{author}")
    void updateScore(String author, int score);
}
