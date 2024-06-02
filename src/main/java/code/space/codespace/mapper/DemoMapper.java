package code.space.codespace.mapper;

import code.space.codespace.pojo.Demo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DemoMapper {
    @Select("select * from demo")
    List<Demo> list();
}
