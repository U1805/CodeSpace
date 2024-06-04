package code.space.codespace.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface LoginMapper {
    @Select("select * from user where username = '${username}' and password = '${password}' and admin = ${admin}")
    int login(String username, String password, int admin);
}
