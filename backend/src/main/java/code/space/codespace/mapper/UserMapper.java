package code.space.codespace.mapper;

import code.space.codespace.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

    @Select("select sum(line) from algorithm where author='${username}'")
    Integer count_line(String username);

    @Select("select count(*) from algorithm where author='${username}'")
    Integer count_algo(String username);

    @Select("select username, score, avatar, email from user where user.username = '${username}'")
    User search(String username);

    @Select("select * from user where username = '${username}' and password = '${password}' and admin = ${admin}")
    int login(String username, String password, int admin);

    @Select("select max(user_id) from user")
    Integer max_user_id();

    @Select("select count(*) from user where username='${username}'")
    Integer find_username(String username);

    @Select("insert into user(user_id, username, password, avatar, email) value(${user_id}, '${username}', '${password}', '', '')")
    Integer register(int user_id, String username, String password);
}
