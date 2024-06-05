package code.space.codespace.mapper;

import code.space.codespace.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

    @Select("select sum(line) from algorithm where author='${username}'")
//    Integer count_algo(String username);
    Integer count_line(String username);

    @Select("select count(*) from algorithm where author='${username}'")
//    Integer count_line(String username);
    Integer count_algo(String username);

    @Select("select username, score, avatar, email from user where user.username = '${username}'")
    User search(String username);
}
