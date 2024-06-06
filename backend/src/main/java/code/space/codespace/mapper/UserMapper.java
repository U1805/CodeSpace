package code.space.codespace.mapper;

import code.space.codespace.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserMapper {

    @Select("select sum(line) from algorithm join user on(author=user_id) where username='${username}'")
    Integer count_line(String username);

    @Select("select count(*) from algorithm join user on(author=user_id) where username='${username}'")
    Integer count_algo(String username);

    @Select("select username, score, avatar, email from user where username = '${username}'")
    User search(String username);

    @Select("select count(*) from user where username = '${username}' and password = '${password}' and admin = ${admin}")
    Integer login(String username, String password, int admin);

    @Select("select count(*) from user where username='${username}'")
    Integer find_username(String username);

    @Select("insert into user(username, password) value('${username}', '${password}')")
    Integer register(String username, String password);

    @Update("update user set score = score+10 where username = #{author}")
    void updateScore(String author);
}
