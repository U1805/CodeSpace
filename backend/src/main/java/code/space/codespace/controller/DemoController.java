package code.space.codespace.controller;

import code.space.codespace.pojo.Demo;
import code.space.codespace.pojo.Result;
import code.space.codespace.service.DemoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
public class DemoController {
    @Autowired
    private DemoService demoService;

    @GetMapping("/demo")
    public Result list(){
        log.info("查询数据");
        List<Demo> demoList = demoService.list();
        return Result.success(demoList);
    }
}
