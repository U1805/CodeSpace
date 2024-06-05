package code.space.codespace.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface RegisterMapper {
    @Select("select max(user_id) from user")
    Integer max_user_id();

    @Select("select count(*) from user where username='${username}'")
    Integer find_username(String username);

    @Select("insert into user(user_id, username, password, avatar, email) value(${user_id}, '${username}', '${password}', '', '')")
    Integer register(int user_id, String username, String password);
}
