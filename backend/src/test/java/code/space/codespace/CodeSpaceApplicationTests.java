package code.space.codespace;

import code.space.codespace.mapper.AlgoMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CodeSpaceApplicationTests {

    @Autowired
    private AlgoMapper algoMapper;
    @Test
    void contextLoads() {
//        algoMapper.searchByKeyword("quick", "");
//        algoMapper.searchById(13);
    }
}
