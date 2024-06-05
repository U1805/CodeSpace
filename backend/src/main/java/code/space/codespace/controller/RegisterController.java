package code.space.codespace.controller;

import code.space.codespace.pojo.RegisterInfo;
import code.space.codespace.pojo.Result;
import code.space.codespace.pojo.LoginInfo;
import code.space.codespace.service.LoginServer;
import code.space.codespace.service.RegisterServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class RegisterController {
    @Autowired
    private RegisterServer registerServer;

    @PostMapping("/register")
    public Result register(@RequestBody RegisterInfo registerInfo){
        Integer res = registerServer.register(registerInfo);
        if(res==0){
            return Result.error("用户名重复");
        }
        else{
            return Result.success("cswaerasd");
        }
    }
}
